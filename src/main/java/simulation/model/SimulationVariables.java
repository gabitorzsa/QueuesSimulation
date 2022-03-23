package simulation.model;

public class SimulationVariables {

    int tasksNumber;
    int queueNumber;

    int maxSimulationTime;

    int minArrivalTime;
    int maxArrivalTime;

    int minServiceTime;
    int maxServiceTime;

    public int getTasksNumber() {
        return tasksNumber;
    }

    public void setTasksNumber(int tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public int getMaxSimulationTime() {
        return maxSimulationTime;
    }

    public void setMaxSimulationTime(int maxSimulationTime) {
        this.maxSimulationTime = maxSimulationTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    @Override
    public String toString() {
        return "SimulationVariables{" +
                "tasksNumber=" + tasksNumber +
                ", queueNumber=" + queueNumber +
                ", maxSimulationTime=" + maxSimulationTime +
                ", minArrivalTime=" + minArrivalTime +
                ", maxArrivalTime=" + maxArrivalTime +
                ", minServiceTime=" + minServiceTime +
                ", maxServiceTime=" + maxServiceTime +
                '}';
    }
}
