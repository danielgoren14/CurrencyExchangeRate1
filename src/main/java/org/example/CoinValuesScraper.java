package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class CoinValuesScraper extends Thread {
    private static String value;
    private final int CHILD_INDEX = 3;
    public CoinValuesScraper() {
      checkValue();
    }

    private void checkValue() {
        new Thread(() -> {
            while (true) {
                if (DisplayPanel.getUserChoice() != null) {
                    try {
                        Document document = Jsoup.connect(Utils.SITE_URL).get();
                        value = document.getElementById(Utils.currency.get(DisplayPanel.getUserChoice())).child(CHILD_INDEX).text();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Utils.sleep(DisplayPanel.getRefreshInterval());
                }
            }
        }).start();
    }

    public static String getValue() {
        return value;
    }
}