package ui;

import simulation.model.SimulationVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetupScreen extends JPanel{
    JLabel tasksNumberLabel;
    JTextField tasksNumber;
    JLabel queueNumbersLabel;
    JTextField queueNumber;
    JLabel maxSimulationTimeLabel;
    JTextField maxSimulationTime;
    JLabel minArrivalTimeLabel;
    JTextField minArrivalTime;
    JLabel maxArrivalTimeLabel;
    JTextField maxArrivalTime;
    JLabel minServiceTimeLabel;
    JTextField minServiceTime;
    JLabel maxServiceTimeLabel;
    JTextField maxServiceTime;
    JButton startButton;

    ActionListener actionListener;

    public SetupScreen(ActionListener actionListener) {
        this.actionListener = actionListener;
        this.initializeUI();
        this.populateWithPlaceholder();
    }


    private void initializeUI(){
        this.setBounds(40,80,200,200);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.tasksNumberLabel = new JLabel("Tasks number: ");
        this.tasksNumber = new JTextField(20);
        this.queueNumbersLabel = new JLabel("Queues number: ");
        this.queueNumber = new JTextField(20);
        this.maxSimulationTimeLabel = new JLabel("Maximum simulation time: ");
        this.maxSimulationTime = new JTextField(20);
        this.minArrivalTimeLabel = new JLabel("Minimum arrival time: ");
        this.minArrivalTime = new JTextField(20);
        this.maxArrivalTimeLabel = new JLabel("Maximum arrival time: ");
        this.maxArrivalTime = new JTextField(20);
        this.minServiceTimeLabel = new JLabel("Minimum service time: ");
        this.minServiceTime = new JTextField(20);
        this.maxServiceTimeLabel = new JLabel("Maximum service time: ");
        this.maxServiceTime = new JTextField(20);

        this.startButton =  new JButton("Start simulation");
        this.startButton.addActionListener(actionListener);

        this.setLayout(new GridLayout(9, 2));
        this.add(this.tasksNumberLabel);
        this.add(this.tasksNumber);
        this.add(this.queueNumbersLabel);
        this.add(this.queueNumber);
        this.add(this.maxSimulationTimeLabel);
        this.add(this.maxSimulationTime);
        this.add(this.minArrivalTimeLabel);
        this.add(this.minArrivalTime);
        this.add(this.maxArrivalTimeLabel);
        this.add(this.maxArrivalTime);
        this.add(this.minServiceTimeLabel);
        this.add(this.minServiceTime);
        this.add(this.maxServiceTimeLabel);
        this.add(this.maxServiceTime);
        this.add(this.startButton);
    }

    private void populateWithPlaceholder(){
        this.tasksNumber.setText("4");
        this.queueNumber.setText("2");
        this.maxSimulationTime.setText("60");
        this.minArrivalTime.setText("2");
        this.maxArrivalTime.setText("30");
        this.minServiceTime.setText("2");
        this.maxServiceTime.setText("4");
    }

    public SimulationVariables getSimulationVariables() {
        SimulationVariables simulationVariables = new SimulationVariables();
        simulationVariables.setTasksNumber(Integer.parseInt(tasksNumber.getText()));
        simulationVariables.setQueueNumber(Integer.parseInt(queueNumber.getText()));
        simulationVariables.setMaxSimulationTime(Integer.parseInt(maxSimulationTime.getText()));
        simulationVariables.setMinArrivalTime(Integer.parseInt(minArrivalTime.getText()));
        simulationVariables.setMaxArrivalTime(Integer.parseInt(maxArrivalTime.getText()));
        simulationVariables.setMinServiceTime(Integer.parseInt(minServiceTime.getText()));
        simulationVariables.setMaxServiceTime(Integer.parseInt(maxServiceTime.getText()));
        return simulationVariables;
    }
}
