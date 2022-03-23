package simulation;

import file.FileManager;
import file.FileManagerImpl;
import simulation.model.SimulationVariables;
import simulation.model.Task;
import ui.UIManager;
import utils.CompareTasksArrivalTime;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
    public SimulationVariables simulationVariables;

    public double totalWaitingTime = 0;
    public double totalServiceTime = 0;
    private File outFile;
    public ArrayList<Task> generatedTasks;

    //queue management and client distribution
    private Scheduler scheduler;

    private FileManager fileManager;

    private UIManager uiManager;

    public SimulationManager(File inFile, File outFile) {
        ArrayList<String> fileResult;
        fileManager = new FileManagerImpl();
        try {
            fileResult = fileManager.readFromFile(inFile, "[1-9]{1}[0-9]*");

            this.simulationVariables = new SimulationVariables();
            simulationVariables.setTasksNumber(Integer.parseInt(fileResult.get(0)));
            simulationVariables.setQueueNumber(Integer.parseInt(fileResult.get(1)));
            simulationVariables.setMaxSimulationTime(Integer.parseInt(fileResult.get(2)));
            simulationVariables.setMinArrivalTime(Integer.parseInt(fileResult.get(3)));
            simulationVariables.setMaxArrivalTime(Integer.parseInt(fileResult.get(4)));
            simulationVariables.setMinServiceTime(Integer.parseInt(fileResult.get(5)));
            simulationVariables.setMaxServiceTime(Integer.parseInt(fileResult.get(6)));

            this.outFile = outFile;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        createTasksAndScheduler();

    }

    public SimulationManager(SimulationVariables simulationVariables) {
        this.simulationVariables = simulationVariables;
        createTasksAndScheduler();
    }

    private void createTasksAndScheduler(){
        generateNRandomTasks();
        scheduler = new Scheduler(simulationVariables.getQueueNumber(), simulationVariables.getTasksNumber());
    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>(simulationVariables.getTasksNumber());
        for (int i = 0; i < simulationVariables.getTasksNumber(); i++) {
            Task t = new Task(i, randomArrivingTime(), randomProcessingTime());
            generatedTasks.add(t);
        }
        // Sort chronologically for arrivingTime
        Collections.sort(generatedTasks, new CompareTasksArrivalTime());
    }


    public void run() {
        AtomicInteger currentTime = new AtomicInteger(0);
        int peakHour = 0;
        int maxTasksConcurrently = 0;
        int nrProcessedClients = 0;

        while (currentTime.get() < simulationVariables.getMaxSimulationTime() &&
                (this.scheduler.areTasksInQueues() || !generatedTasks.isEmpty())) {
            System.out.println("--- Time: " + currentTime.get() + " --- Remaining: " + this.generatedTasks.size() + " ---");

            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime.get()) {
                scheduler.dispatchTask(generatedTasks.get(0));
                nrProcessedClients++;
                totalServiceTime += generatedTasks.get(0).getProcessingPeriod();
                generatedTasks.remove(generatedTasks.get(0));
            }

            if(this.outFile != null) {
                String result = getResult(currentTime.get());
                fileManager.writeToFile(this.outFile, result);
            }

            if(this.scheduler.getTotalCurrentTasks() > maxTasksConcurrently){
                // System.out.println(this.scheduler.getTotalCurrentTasks());
                maxTasksConcurrently = this.scheduler.getTotalCurrentTasks();
                peakHour = currentTime.get();
            }

            if(this.uiManager != null){
                updateUI();
            }

            try {
                Thread.sleep(100);
                currentTime.incrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(this.outFile != null) {
            String formatting ="\n----------- Results ----------\n";
            fileManager.writeToFile(this.outFile, formatting);
            String serviceData = String.format("Average service time: %s\n", (totalServiceTime / nrProcessedClients));
            fileManager.writeToFile(this.outFile, serviceData);
            String peakData = String.format("Peak hour: %s\n", peakHour);
            fileManager.writeToFile(this.outFile, peakData);
        }
        if(this.uiManager != null){
            updateUI();
        }
        this.scheduler.stopServers();
    }

    private void updateUI(){
        this.uiManager.updateQueueInfo(this.scheduler.getServers());
    }

    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public int randomProcessingTime() {
        return (simulationVariables.getMinServiceTime() + (int) (Math.random() * (simulationVariables.getMaxServiceTime() -
                simulationVariables.getMinServiceTime())));
    }

    public int randomArrivingTime() {
        return (simulationVariables.getMinArrivalTime() + (int) (Math.random() * (simulationVariables.getMaxArrivalTime() -
                simulationVariables.getMinArrivalTime())));
    }

    private String getResult(int currentTime) {
        String result = "\nTime: " + currentTime + "\n";
        result = result + "Waiting clients: ";
        for (Task i : generatedTasks) {
            result = result + i.toString();
        }
        result = result + "\n" + scheduler.toString();
        return result;
    }

}