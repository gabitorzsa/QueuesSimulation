package simulation.strategy;

import simulation.Server;
import simulation.model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    public void addTask(List<Server> servers, Task t) {
        long minTime = 320000;
        Server serverWithShortestWaitingTime = servers.get(0);
        for (Server server : servers) {
            int serverQueueWaitingTime = server.getWaitingPeriod().get();
            if (serverQueueWaitingTime < minTime) {
                minTime = serverQueueWaitingTime;
                serverWithShortestWaitingTime = server;
            }
        }
        serverWithShortestWaitingTime.setOpen(true);
        serverWithShortestWaitingTime.addTask(t);
    }
}


