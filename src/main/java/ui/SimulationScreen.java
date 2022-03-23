package ui;

import simulation.Server;
import simulation.model.SimulationVariables;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SimulationScreen extends JPanel {

    JButton finishButton;
    SimulationVariables simulationVariables;
    ActionListener actionListener;

    List<QueuePanel> queuePanelList = new ArrayList<>();

    public SimulationScreen(ActionListener actionListener, SimulationVariables simulationVariables) {
        this.actionListener = actionListener;
        this.simulationVariables = simulationVariables;
        this.initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < this.simulationVariables.getQueueNumber(); i++) {
            QueuePanel queuePanel = new QueuePanel();
            this.queuePanelList.add(queuePanel);
            this.add(queuePanel);
        }

        this.finishButton = new JButton("Finish simulation");
        this.finishButton.addActionListener(actionListener);
        this.add(finishButton);
    }

    public void updateQueueInfo(List<Server> servers) {
        for (int i = 0; i < queuePanelList.size(); i++) {
            queuePanelList.get(i).setTasks(servers.get(i).getTasks());

        }
    }
}
