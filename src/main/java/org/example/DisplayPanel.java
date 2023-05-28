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
    private  final int BACK_BUTTON_Y = 600;
    private  final int CALCULATE_BUTTON_Y = 250;
    private  final int EXCHANGE_NAME_LABEL_Y = 25;
    private  final int EXCHANGE_NAME_LABEL_SIZE = 35;
    private  final int CURRENT_VALUE_LABEL_Y = 100;
    private  final int CURRENT_VALUE_LABEL_SIZE = 16;
    private  final int SLIDER_LABEL_Y = 300;
    private  final int SLIDER_Y = SLIDER_LABEL_Y+75;
    private  final int SLIDER_WIDTH = 350;
    private  final int SLIDER_HEIGHT = 75;
    private  final int MINIMUM_SLIDER = 0;
    private  final int MAXIMUM_SLIDER =1000;
    private  final int MAJOR_TICK_SPACING = 100;
    private  final int INPUT_TEXT_FIELD_Y =200;
    private  final int INPUT_TEXT_FIELD_HEIGHT = 30;

    private  final int MINOR_TICK_SPACING = 25;
    private  final int SLIDER_VALUE = 1;


    private JButton backButton;
    private JLabel currentValueLabel;
    private JLabel exchangeNameLabel;
    private JTextField inputTextField;
    private JButton calculateButton;
    private JLabel conversionLabel;
    private JSlider delaySlider;
    private JLabel delaySliderLabel;

    public DisplayPanel() {
        this.setVisible(false);
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.gray);


        this.backButton = new JButton("BACK");
        this.backButton.setBounds((Window.WIDTH - BUTTON_WIDTH) / 2, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, backButton);
        this.backButton.addActionListener((event) -> {
            Window.changePanel(Window.mainMenu, this);
            this.delaySlider.setValue(SLIDER_VALUE);
        });

        this.conversionLabel = new JLabel();
        this.conversionLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, CALCULATE_BUTTON_Y, LABEL_WIDTH, LABEL_HEIGHT);
        this.conversionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.conversionLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.conversionLabel.setVisible(true);
        this.conversionLabel.setEnabled(true);
        this.add(this.conversionLabel);


        this.calculateButton = new JButton("Calculate");
        this.calculateButton.setBounds((Window.WIDTH - BUTTON_WIDTH) / 2, CALCULATE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.calculateButton.setVisible(false);
        Utils.addButton(this, calculateButton);
        this.calculateButton.addActionListener((event) -> {
            this.conversionLabel.setText("your exchange worth : "+ String.valueOf(Double.parseDouble(CoinValuesScraper.getValue()) * this.getInputValue()));
        });


        this.currentValueLabel = new JLabel();
        this.currentValueLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, CURRENT_VALUE_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        this.currentValueLabel.setFont(new Font("Ariel", Font.BOLD, CURRENT_VALUE_LABEL_SIZE));
        this.currentValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.currentValueLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.currentValueLabel.setVisible(true);
        this.add(currentValueLabel);
        setUserChoice("USD/EUR");
        displayCurrentCurrency();
      //  displayCurrentCurrency1();


        this.exchangeNameLabel = new JLabel(userChoice);
        this.exchangeNameLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, EXCHANGE_NAME_LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        this.exchangeNameLabel.setFont(new Font("Ariel", Font.BOLD, EXCHANGE_NAME_LABEL_SIZE));
        this.exchangeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.exchangeNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(exchangeNameLabel);


        this.inputTextField = new JTextField("enter sum to calculate current value");
        this.inputTextField.setBounds((Window.WIDTH - LABEL_WIDTH) / 2, INPUT_TEXT_FIELD_Y, LABEL_WIDTH, INPUT_TEXT_FIELD_HEIGHT);
        this.inputTextField.setHorizontalAlignment(SwingConstants.CENTER);
        this.inputTextField.setVisible(true);
        this.inputTextField.setEnabled(true);
        this.add(this.inputTextField);


        this.delaySlider = new JSlider();
        this.delaySlider.setBounds((Window.WIDTH - SLIDER_WIDTH) / 2, SLIDER_Y, SLIDER_WIDTH, SLIDER_HEIGHT);
        this.delaySlider.setMinimum(MINIMUM_SLIDER);
        this.delaySlider.setMaximum(MAXIMUM_SLIDER);
        this.delaySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        this.delaySlider.setPaintTicks(true);
        this.delaySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        this.delaySlider.setPaintLabels(true);
        this.delaySlider.setPaintTrack(true);
        this.delaySlider.setValue(SLIDER_VALUE);
        this.delaySlider.setName("hey-");
        this.delaySlider.setToolTipText(this.delaySlider.getValue()+"");
        this.delaySlider.addChangeListener(e ->{
            refreshInterval = this.delaySlider.getValue();
            this.delaySlider.setToolTipText(this.delaySlider.getValue()+"");
            this.delaySliderLabel.setText("refresh exchange rate delay , current delay is : "+ this.delaySlider.getValue() + " seconds" );
        });
        this.add(delaySlider);


        this.delaySliderLabel = new JLabel("refresh exchange rate delay , current delay is : "+ this.delaySlider.getValue() + " seconds"  );
        this.delaySliderLabel.setBounds((Window.WIDTH - LABEL_WIDTH) / 2,SLIDER_LABEL_Y,LABEL_WIDTH,LABEL_HEIGHT);
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