import simulation.SimulationManager;
import ui.UIManager;

import java.io.File;

public class Main {
    public static void main(String args[]) {
     startFileSimulation();
//        startUISimulation();
    }

    static void startFileSimulation() {
        try {
            File inputFile = new File("in-test-1.txt");
            File outputFile = new File("out-test-1.txt");

            SimulationManager gen = new SimulationManager(inputFile, outputFile);
            Thread simThread = new Thread(gen);
            simThread.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void startUISimulation() {
        try {
            UIManager uiManager = new UIManager();
            uiManager.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
