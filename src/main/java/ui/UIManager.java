package ui;

import simulation.Server;
import simulation.SimulationManager;
import simulation.model.SimulationVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UIManager extends JFrame {

    SetupScreen setupScreen;
    SimulationScreen simulationScreen;

    ActionListener setupScreenActionListener;
    ActionListener simulationScreenActionListener;

    SimulationManager simulationManager;

    public UIManager() throws HeadlessException {
        setTitle("Simulation setup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(900, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        initializeActionListeners();
        displaySetupScreen();
    }
    public void initializeAndStartSimulation(SimulationVariables simulationVariables){
        setTitle("Simulation running...");
        this.simulationScreen = new SimulationScreen(simulationScreenActionListener, simulationVariables);
        this.remove(setupScreen);
        this.add(simulationScreen);
        this.revalidate();

        this.simulationManager = new SimulationManager(simulationVariables);
        this.simulationManager.setUIManager(this);
        Thread simThread = new Thread(simulationManager);
        simThread.start();
    }
    public void displaySetupScreen(){
        setTitle("Simulation setup");
        this.setupScreen = new SetupScreen(setupScreenActionListener);
        if(simulationScreen != null){
            this.remove(simulationScreen);
        }
        this.add(setupScreen);
        this.revalidate();
    }


    void initializeActionListeners(){
        this.setupScreenActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationVariables simulationVariables = setupScreen.getSimulationVariables();
                initializeAndStartSimulation(simulationVariables);
            }
        };

        this.simulationScreenActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySetupScreen();
            }
        };
    }

    public void updateQueueInfo(List<Server> servers){
        this.simulationScreen.updateQueueInfo(servers);
    }

}
