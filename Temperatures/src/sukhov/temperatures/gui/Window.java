package sukhov.temperatures.gui;

import sukhov.temperatures.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Window {
    private JFrame frame;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    private Converter converter;
    private ScalesBox box;

    public Window() {
        textInput();
        textOutput();
        convert();

        createComboBox1();
        createComboBox2();

        createPanel();
        createFrame();
    }

    private void createFrame() {
        frame = new JFrame("Temperatures");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 130);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());

        frame.add(panel1);
        frame.add(textField1);
        frame.add(textField2);
        frame.add(button);
    }

    private void createPanel() {
        panel1 = new JPanel();

        panel1.add(comboBox1);
        panel1.add(comboBox2);
    }

    private JComboBox comboBox() {
        JComboBox<String> comboBox = new JComboBox<>();

        comboBox.addItem("Цельсия");
        comboBox.addItem("Фаренгейта");
        comboBox.addItem("Кельвин");
        comboBox.addItem("Реомюра");

        return comboBox;
    }

    private ScalesBox selectComboBox(int selectedIndex) {
        switch (selectedIndex) {
            case 0:
                box = ScalesBox.CELSIUS;
                break;
            case 1:
                box = ScalesBox.FAHRENHEIT;
                break;
            case 2:
                box = ScalesBox.KELVIN;
                break;
            case 3:
                box = ScalesBox.REAUMUR;
                break;
        }

        return box;
    }

    private void createComboBox1() {
        comboBox1 = comboBox();
    }

    private void createComboBox2() {
        comboBox2 = comboBox();
    }

    private void textInput() {
        textField1 = new JTextField(13);
        textField1.setToolTipText("Введите значение");

        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    Double.parseDouble(textField1.getText());
                    textField1.setToolTipText("");
                } catch (NumberFormatException n) {
                    textField1.setToolTipText("Неверные значения");
                }
            }
        });
    }

    private void textOutput() {
        textField2 = new JTextField(13);
        textField2.setEditable(false);
    }

    private void convert() {
        button = new JButton("Конвертировать");
        converter = new Converter();

        button.addActionListener(e -> {
            try {
                converter.input(Double.parseDouble(textField1.getText()), selectComboBox(comboBox1.getSelectedIndex()));
                textField2.setText(Double.toString(converter.output(selectComboBox(comboBox2.getSelectedIndex()))));

            } catch (NumberFormatException ignored) {
            }
        });
    }
}
