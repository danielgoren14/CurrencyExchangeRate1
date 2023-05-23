package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_X = 225;
    private static final int INITIAL_BUTTON_Y = 50;

    private JButton eurUsd;
    private JButton gbpUsd;
    private JButton usdJpy;
    private JButton usdIls;
    private JButton eurIls;

    public MainPanel() {
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.gray);

        this.eurUsd = new JButton("EUR/USD ");
        this.eurUsd.setBounds(BUTTON_X, INITIAL_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, eurUsd);

        this.gbpUsd = new JButton("GBP/USD");
        this.gbpUsd.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, gbpUsd);

        this.usdJpy = new JButton("USD/JPY");
        this.usdJpy.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, usdJpy);

        this.usdIls = new JButton("USD/ILS");
        this.usdIls.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, usdIls);

        this.eurIls = new JButton("EUR/ILS");
        this.eurIls.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, eurIls);


        this.eurIls.addActionListener((event) -> {
            DisplayPanel.setNamename(eurIls.getText());
            Window.changePanel(Window.subMenu, this);
        });

        this.usdJpy.addActionListener((event) -> {
            Window.changePanel(Window.subMenu, this);
        });

        this.usdIls.addActionListener((event) -> {
            Window.changePanel(Window.subMenu, this);;
        });

        this.gbpUsd.addActionListener((event) -> {
            Window.changePanel(Window.subMenu, this);
        });

        this.eurUsd.addActionListener((event) -> {
            Window.changePanel(Window.subMenu, this);
        });
    }

}

