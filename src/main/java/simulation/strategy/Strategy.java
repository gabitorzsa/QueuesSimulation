package simulation.strategy;
import simulation.Server;
import simulation.model.Task;

import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Task t);
}


