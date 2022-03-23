package utils;

import simulation.model.Task;

import java.util.Comparator;

public class CompareTasksArrivalTime implements Comparator<Task>{
    public int compare(Task x, Task y)
    {
        return x.getArrivalTime()-y.getArrivalTime();
    }
}