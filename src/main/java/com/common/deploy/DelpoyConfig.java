package com.common.deploy;

import java.util.ResourceBundle;


/**
 * @author lzp
 */
public class DelpoyConfig {


    private static Object lock = new Object();
    private static DelpoyConfig config = null;
    private static ResourceBundle rb = null;
    private static final String CONFIG_FILE = "common/deploy/deployConfig";

    private DelpoyConfig() {
        rb = ResourceBundle.getBundle(CONFIG_FILE);
    }

    public static DelpoyConfig getInstance() {
        synchronized (lock) {
            if (null == config) {
                config = new DelpoyConfig();
            }
        }
        return (config);
    }

    public String getValue(String key) {
        return (rb.getString(key));
    }
}

