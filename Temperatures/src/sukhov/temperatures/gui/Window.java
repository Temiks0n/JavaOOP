package sukhov.temperatures.gui;

import sukhov.temperatures.Converter;
import sukhov.temperatures.scales.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window {
    private JFrame window;
    private JPanel panelScales;
    private JComboBox listScalesInput;
    private JComboBox listScalesOutput;
    private JTextField input;
    private JTextField output;
    private JButton convert;
    private Converter converter;

    public Window() {
        textInput();
        textOutput();
        convert();

        createListScalesInput();
        createListScalesOutput();
        createPanelScales();

        SwingUtilities.invokeLater(this::createWindow);
    }

    private void createWindow() {
        window = new JFrame("Temperatures");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 130);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        window.setLayout(new FlowLayout());

        window.add(panelScales);
        window.add(input);
        window.add(output);
        window.add(convert);
    }

    private void createPanelScales() {
        panelScales = new JPanel();

        panelScales.add(listScalesInput);
        panelScales.add(listScalesOutput);
    }

    private JComboBox getScales() {
        JComboBox<String> comboBox = new JComboBox<>();

        comboBox.addItem("Цельсия");
        comboBox.addItem("Фаренгейта");
        comboBox.addItem("Кельвин");
        comboBox.addItem("Реомюра");

        return comboBox;
    }

    private Scale selectScale(int selectedIndex) {
        Scale scale = null;

        switch (selectedIndex) {
            case 0:
                scale = new CelsiusScale();
                break;
            case 1:
                scale = new FahrenheitScale();
                break;
            case 2:
                scale = new KelvinScale();
                break;
            case 3:
                scale = new ReaumerScale();
                break;
        }

        return scale;
    }

    private void createListScalesInput() {
        listScalesInput = getScales();
    }

    private void createListScalesOutput() {
        listScalesOutput = getScales();
    }

    private void textInput() {
        input = new JTextField(13);
        input.setToolTipText("Введите значение");

        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    Double.parseDouble(input.getText());
                    input.setToolTipText("");
                } catch (NumberFormatException n) {
                    input.setToolTipText("Неверные значения");
                }
            }
        });
    }

    private void textOutput() {
        output = new JTextField(13);
        output.setEditable(false);
    }

    private void convert() {
        convert = new JButton("Конвертировать");
        converter = new Converter();

        convert.addActionListener(e -> {
            try {
                converter.getInput(Double.parseDouble(input.getText()), selectScale(listScalesInput.getSelectedIndex()));
                output.setText(Double.toString(converter.getOutput(selectScale(listScalesOutput.getSelectedIndex()))));
            } catch (NumberFormatException ignored) {
            }
        });
    }
}
