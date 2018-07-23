package com.common;

import java.util.HashMap;
import java.util.Map;

public class CusSession {
    private static final ThreadLocal threadSession = new ThreadLocal();
    private Map map = new HashMap();

    private CusSession() {
    }

    public static CusSession getSession() {
        CusSession s = (CusSession) threadSession.get();
        if (s == null) {
            s = new CusSession();
            threadSession.set(s);
        }
        return s;
    }

    public void set(Object key, Object value) {
        map.put(key, value);
    }

    public Object get(Object key) {
        return map.get(key);
    }
}
