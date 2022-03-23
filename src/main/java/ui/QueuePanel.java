package ui;

import simulation.model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
// one line with rect and ovals

public class QueuePanel extends JPanel {

    int id;
    int noOfTasks;

    BlockingQueue<Task> tasks = new ArrayBlockingQueue<>(1000);

    private static int SPACE_BETWEEN_TASKS = 20;

    public QueuePanel() {
        setPreferredSize(new Dimension(800, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,25,50);

        for(int i = 0; i < this.tasks.size(); i++){
            g.setColor(Color.ORANGE);
            g.fillOval(50 + SPACE_BETWEEN_TASKS * i, 18,15,15);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfTasks() {
        return noOfTasks;
    }

    public void setNoOfTasks(int noOfTasks) {
        this.noOfTasks = noOfTasks;
    }

    public void addTask(){
        this.noOfTasks++;
        revalidate();
        repaint();
    }

    public void setTasks(BlockingQueue<Task> tasks){
        this.tasks = tasks;
        revalidate();;
        repaint();
    }


}
