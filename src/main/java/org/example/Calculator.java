package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private JPanel panel;

    private String operator;
    private double num1, num2, result;

    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        panel = new JPanel();

        operator = "";
        num1 = num2 = result = 0;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(textField, BorderLayout.NORTH);

        panel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }

                textField.setText(String.valueOf(result));
                operator = "";
            } else {
                if (!operator.equals("")) return;

                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
