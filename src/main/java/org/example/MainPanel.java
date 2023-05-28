package org.example;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private  final int BUTTON_WIDTH = 100;
    private  final int BUTTON_HEIGHT = 30;
    private  final int TITLE_Y = 50;
    private  final int TITLE_FONT_SIZE = 30;
    private  final int BUTTON_X = 225;
    private  final int INITIAL_BUTTON_Y = 150;
    private  final int MARGIN_FROM_BUTTON = 50;
    private  final int LABEL_WIDTH = 350;
    private  final int LABEL_HEIGHT = 100;

    private JButton usdEur;
    private JButton gbpUsd;
    private JButton usdJpy;
    private JButton usdIls;
    private JButton eurIls;
    private JLabel title;

    public MainPanel() {
        this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.gray);

        this.usdEur = new JButton(Utils.USD_EUR);
        this.usdEur.setBounds(BUTTON_X, INITIAL_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, usdEur);

        this.gbpUsd = new JButton(Utils.GBP_USD);
        this.gbpUsd.setBounds(BUTTON_X, INITIAL_BUTTON_Y + MARGIN_FROM_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, gbpUsd);

        this.usdJpy = new JButton(Utils.USD_JPY);
        this.usdJpy.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 2 * MARGIN_FROM_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, usdJpy);

        this.usdIls = new JButton(Utils.USD_ILS);
        this.usdIls.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 3 * MARGIN_FROM_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, usdIls);

        this.eurIls = new JButton(Utils.EUR_ILS);
        this.eurIls.setBounds(BUTTON_X, INITIAL_BUTTON_Y + 4 * MARGIN_FROM_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        Utils.addButton(this, eurIls);


        this.eurIls.addActionListener((event) -> buttonAction(Utils.EUR_ILS));

        this.usdJpy.addActionListener((event) -> buttonAction(Utils.USD_JPY));

        this.usdIls.addActionListener((event) -> buttonAction(Utils.USD_ILS));

        this.gbpUsd.addActionListener((event) -> buttonAction(Utils.GBP_USD));

        this.usdEur.addActionListener((event) -> buttonAction(Utils.USD_EUR));

        this.title = new JLabel("currency exchange");
        this.title.setBounds((Window.WIDTH - LABEL_WIDTH) / 2,TITLE_Y,LABEL_WIDTH,LABEL_HEIGHT);
        this.title.setFont(new Font("Ariel", Font.BOLD, TITLE_FONT_SIZE));
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
        this.title.setVerticalAlignment(SwingConstants.CENTER);
        this.add(title);
    }

    private void buttonAction(String string){
        DisplayPanel displayPanel = new DisplayPanel();
        displayPanel.setUserChoice(string);
        Window.changePanel(Window.subMenu, this);
    }

}