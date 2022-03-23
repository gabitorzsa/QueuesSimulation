package simulation;

import simulation.model.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;

    private int id;
    private boolean isOpen;
    private AtomicInteger peakHour;
    private AtomicInteger tasksDone; // cati clienti au terminat
    private AtomicInteger waitingPeriod; // timpul total cat a fost deschisa coada
    private AtomicInteger averageWaitingTime;


    public Server(int id, int maxTasksPerServer) {
        this.tasks = new ArrayBlockingQueue<>(maxTasksPerServer);
        this.id = id;
        this.isOpen = true;
        this.tasksDone = new AtomicInteger(0);
        this.waitingPeriod = new AtomicInteger(0);
        this.averageWaitingTime = new AtomicInteger(0);
        System.out.println("Server with ID: " + id + " was created!");
    }

    public void run() {
        while (isOpen) {
            Task nextTask = tasks.peek();
            if (nextTask != null) {
                serveClient(nextTask);
            }
        }
    }

    public void serveClient(Task client) {
        sleep(1);
        client.setProcessingPeriod(client.getProcessingPeriod() - 1);

        if (client.getProcessingPeriod() <= 0) {
            tasks.remove(client);
        }
        System.out.println("Queue #" + this.getId() + " :" + this.tasks.toString());
        this.waitingPeriod.decrementAndGet();
    }


    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        this.waitingPeriod.addAndGet(newTask.getProcessingPeriod());

//        this.averageWaitingTime = this.waitingPeriod / this.tasksDone.get();
    }

    void sleep(int seconds) {
        try {
            Thread.sleep(100 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getId() {
        return id;
    }

    public int getTasksLength() {
        return this.tasks.size();
    }

    public double getAverageWaitingTime() {
        return ((double) this.tasksDone.get() / this.waitingPeriod.get());
    }


    public void setOpen(boolean open) {
        isOpen = open;
    }


    public AtomicInteger getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(AtomicInteger tasksDone) {
        this.tasksDone = tasksDone;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public BlockingQueue<Task>  getTasks(){
        return this.tasks;
    }

    public String toString() {
        String result;
        if (tasks.peek() == null || tasks.peek().getProcessingPeriod() == 0) {
            result = "closed";
        } else {
            result = tasks.toString();
        }
        return result;
    }

}
