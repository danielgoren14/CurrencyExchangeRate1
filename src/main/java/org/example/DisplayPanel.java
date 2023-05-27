package org.example;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private static String userChoice;
    private static int refreshInterval;


    private  final int LABEL_HEIGHT = 100;
    private  final int LABEL_WIDTH = 350;
    private  final int BUTTON_WIDTH = 100;
    private  final int BUTTON_HEIGHT = 30;
    private  final int BUTTON_X = 225;
    private  final int BACK_BUTTON_Y = 600;
    private  final int CALCULATE_BUTTON_Y = 400;


    private JButton backButton;
    private JLabel currentValueLabel;
    private JLabel exchangeNameLabel;
    private JTextField inputTextField;
    private JButton calculateButton;
    private JLabel convertionLabel;
    private JSlider delaySlider;
    private JLabel delaySliderLabel;

    public DisplayPanel() {
        this.setVisible(false);
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.gray);


        this.backButton = new JButton("BACK");
        this.backButton.setBounds(BUTTON_X, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, backButton);
        this.backButton.addActionListener((event) -> {
            Window.changePanel(Window.mainMenu, this);
        });

        this.convertionLabel = new JLabel();
        this.convertionLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, 250, LABEL_WIDTH, LABEL_HEIGHT);
        this.convertionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.convertionLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.convertionLabel.setVisible(true);
        this.convertionLabel.setEnabled(true);
        this.add(this.convertionLabel);


        this.calculateButton = new JButton("Calculate");
        this.calculateButton.setBounds(BUTTON_X, CALCULATE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.calculateButton.setVisible(false);
        Utils.addButton(this, calculateButton);
        this.calculateButton.addActionListener((event) -> {
            this.convertionLabel.setText("your exchange worth : "+ String.valueOf(Double.parseDouble(CoinValuesScraper.getValue()) * this.getInputValue()));
        });


        this.currentValueLabel = new JLabel();
        this.currentValueLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, 100, LABEL_WIDTH, LABEL_HEIGHT);
        this.currentValueLabel.setFont(new Font("Ariel", Font.BOLD, 16));
        this.currentValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.currentValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.currentValueLabel.setVisible(true);
        this.add(currentValueLabel);
        setUserChoice("USD/EUR");
        displayCurrentCurrency();


        this.exchangeNameLabel = new JLabel(userChoice);
        this.exchangeNameLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, 25, LABEL_WIDTH, LABEL_HEIGHT);
        this.exchangeNameLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        this.exchangeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.exchangeNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(exchangeNameLabel);


        this.inputTextField = new JTextField("enter sum to calculate current value");
        this.inputTextField.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, 200, LABEL_WIDTH, 30);
        this.inputTextField.setHorizontalAlignment(SwingConstants.CENTER);
        this.inputTextField.setVisible(true);
        this.inputTextField.setEnabled(true);
        this.add(this.inputTextField);




        this.delaySlider = new JSlider();
        this.delaySlider.setBounds(100, 500, 350, 75);
        this.delaySlider.setMinimum(0);
        this.delaySlider.setMaximum(1000);
        this.delaySlider.setMajorTickSpacing(100);
        this.delaySlider.setPaintTicks(true);
        this.delaySlider.setMinorTickSpacing(25);
        this.delaySlider.setPaintLabels(true);
        this.delaySlider.setPaintTrack(true);
        this.delaySlider.setValue(1);
        this.delaySlider.setName("hey-");
        this.delaySlider.setToolTipText(this.delaySlider.getValue()+"");
        this.delaySlider.addChangeListener(e ->{
            refreshInterval = this.delaySlider.getValue();
            this.delaySlider.setToolTipText(this.delaySlider.getValue()+"");
            this.delaySliderLabel.setText("refresh exchange rate delay , current delay is : "+ this.delaySlider.getValue() + " seconds" );
        });
        this.add(delaySlider);


        this.delaySliderLabel = new JLabel("refresh exchange rate delay , current delay is : "+ this.delaySlider.getValue() + " seconds"  );
        this.delaySliderLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2,425,LABEL_WIDTH,LABEL_HEIGHT);
        this.delaySliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.delaySliderLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(delaySliderLabel);

    }

    public static String getUserChoice() {
        return userChoice;
    }

    public void displayCurrentCurrency() {
        CoinValuesScraper scraper = new CoinValuesScraper();
        new Thread(() -> {
            while (true) {
                setLabelText(userChoice);
                this.currentValueLabel.setText("Current exchange rate :" + scraper.getValue());
            }
        }).start();
    }

    public static void setUserChoice(String userChoice) {
        DisplayPanel.userChoice = userChoice;
    }

    private void setLabelText(String text) {
        if (this.exchangeNameLabel != null) {
            this.exchangeNameLabel.setText(text);
        }
    }

    public static int getRefreshInterval() {
        return refreshInterval;
    }

    public double getInputValue() {
        try {
            return Double.parseDouble(this.inputTextField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}