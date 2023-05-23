package org.example;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private static String userChoice;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_X = 225;
    private static final int BACK_BUTTON_Y = 600;
    private static final int CALCULATE_BUTTON_Y = 400;

    private JButton back;
    private JLabel currentValue;
    private JLabel textAboveInput;
    private JLabel textAboveOutput;
    private JTextField input;
    private JButton calculate;
    private JLabel output;

    public DisplayPanel() {
        this.setVisible(false);
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.gray);

        this.calculate = new JButton("Calculate");
        this.calculate.setBounds(BUTTON_X, CALCULATE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, calculate);

        this.back = new JButton("BACK");
        this.back.setBounds(BUTTON_X, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, back);
        this.back.addActionListener((event) -> {
            Window.changePanel(Window.mainMenu, this);
        });

        this.calculate = new JButton("Calculate");
        this.calculate.setBounds(BUTTON_X, CALCULATE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, calculate);
        this.calculate.setVisible(false);
        this.calculate.setEnabled(false);

        this.currentValue = new JLabel();
        this.currentValue.setBounds(250, 250, 100, 100);
        this.currentValue.setVisible(true);
        this.add(currentValue);
        setNamename("USD/EUR");
        displayCurrentCurrency();
    }


    public void displayCurrentCurrency() {
        CurrencyPairs currencyPairs = new CurrencyPairs(userChoice);
        new Thread(() -> {
            while (true) {
                this.currentValue.setText(currencyPairs.getValue());
            }
        }).start();
    }

    public static void setNamename(String namename) {
        DisplayPanel.userChoice = namename;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // CurrencyPairs currencyPairs = new CurrencyPairs(userChoice);
     //   this.currentValue.setText(currencyPairs.getValue());
        repaint();
    }


}
