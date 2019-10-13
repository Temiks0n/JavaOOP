package sukhov.temperatures.main;

import sukhov.temperatures.gui.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
        });
    }
}
