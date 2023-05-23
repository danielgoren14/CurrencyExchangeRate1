package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CurrencyPairs extends Thread {
    private String value;

    public CurrencyPairs(String name) {
        checkValue(name);
    }

    private void checkValue(String name) {
        new Thread(() -> {
            while (true) {
                if (name != null) {
                    try {
                        Document document = Jsoup.connect(Utils.SITE_URL).get();
                        this.value = document.getElementById(Utils.currency.get(name)).child(3).text();
                        Utils.sleep(1);
                    } catch (IOException e) {
                       throw new RuntimeException(e);

                    }
                }
            }
        }).start();
    }

    public String getValue() {
        return value;
    }
}
