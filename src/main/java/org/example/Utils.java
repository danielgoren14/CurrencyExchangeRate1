package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public final static String SITE_URL = "https://il.investing.com/currencies/";
    public static final Map<String , String> currency = new HashMap<>(){{
        put("USD/EUR" ,"pair_1");
        put("GBP/USD" , "pair_2");
        put("USD/JPY" , "pair_3");
        put("USD/ILS" , "pair_9");
        put("EUR/ILS" , "pair_10");
    }
    };
    public static void sleep (int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addButton(JPanel panel , JButton currentButton) {
        currentButton.setVisible(true);
        currentButton.setEnabled(true);
        currentButton.setHorizontalTextPosition(JButton.CENTER);
        currentButton.setVerticalTextPosition(JButton.CENTER);
        currentButton.setContentAreaFilled(true);
        currentButton.setBackground(Color.WHITE);
        panel.add(currentButton);
    }


}
