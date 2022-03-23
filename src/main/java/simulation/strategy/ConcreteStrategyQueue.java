package simulation.strategy;

import simulation.Server;
import simulation.model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    public void addTask(List<Server> servers, Task t) {
        int minQueue = 32000;
        Server serverWithShortestQueue = servers.get(0);
        for (Server server:servers) {
            int serverQueueLength = server.getTasksLength();
            if(serverQueueLength < minQueue){
                minQueue = serverQueueLength;
                serverWithShortestQueue = server;
            }
        }
        serverWithShortestQueue.setOpen(true);
        serverWithShortestQueue.addTask(t);
    }

}

