package sukhov.temperature.main;

import sukhov.temperature.gui.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
        });
    }
}
