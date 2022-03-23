package simulation;

import simulation.model.Task;
import simulation.strategy.ConcreteStrategyQueue;
import simulation.strategy.ConcreteStrategyTime;
import simulation.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;

    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int noServers, int noTasks) {
        this.changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
        this.maxNoServers = noServers;
        this.maxTasksPerServer = noTasks;
        this.servers = new ArrayList<>(noServers);
        for (int i = 0; i < noServers; i++) {
            Server server = new Server(i, maxTasksPerServer);
            servers.add(server);
            // Add to thead list
            Thread serverThread = new Thread(server);
            serverThread.start();
        }
    }


    public void changeStrategy(SelectionPolicy policy) {
        // apply strategy patter to instantiate the strategy with the concrete strategy corresponding to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        else if (policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }


    public void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }

    public boolean areTasksInQueues(){
        for (Server i : servers) {
            if(i.getTasksLength() > 0){
                return true;
            }
        }
        return false;
    }

    public void stopServers(){
        for (Server server : servers){
            server.setOpen(false);
        }
    }

    public int getTotalCurrentTasks(){
        int totalTasks = 0;
        for (Server q : servers) {
            totalTasks += q.getTasksLength();
        }
        return totalTasks;
    }

    public String toString() {
        String rezultat = "";
        for (Server i : servers) {
            rezultat += "Queue " + i.getId() + ": " + i.toString() + "\n";
        }

        return rezultat;
    }



}
