package com.common;

import java.util.ResourceBundle;


/**
 * @author lzp
 */
public class BookingConfig {


    private static Object lock = new Object();
    private static BookingConfig config = null;
    private static ResourceBundle rb = null;
    private static final String CONFIG_FILE = "config";

    private BookingConfig() {
        rb = ResourceBundle.getBundle(CONFIG_FILE);
    }

    public static BookingConfig getInstance() {
        synchronized (lock) {
            if (null == config) {
                config = new BookingConfig();
            }
        }
        return (config);
    }

    public String getValue(String key) {
        return (rb.getString(key));
    }
}

