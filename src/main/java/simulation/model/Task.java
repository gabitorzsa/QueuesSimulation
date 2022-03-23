package simulation.model;

public class Task {
    private int id;
    private int arrivalTime;
    private int processingPeriod; // t_service

    public Task(int id, int arrivalTime, int processingPeriod) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingPeriod = processingPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    public String toString() {
        return "(" + id + "," + arrivalTime + "," + processingPeriod + ")";
    }
}
