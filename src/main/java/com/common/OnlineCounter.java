package com.common;

import org.jboss.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCounter extends HttpServlet implements HttpSessionListener {
    private static Logger log = Logger.getLogger(OnlineCounter.class);
    private static final long serialVersionUID = 1L;
    private static int sessionCounter = 0;

    public OnlineCounter() {
        //log.info("OnlineCounter initialized.");
    }

    public void sessionCreated(HttpSessionEvent se) {
        if (sessionCounter < 0) {
            sessionCounter = 0;
        }
        sessionCounter++;
        log.info("session created:" + sessionCounter);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        sessionCounter--;
        if (sessionCounter < 0) {
            sessionCounter = 0;
        }
        log.info("session destroied");
    }

    public static int getOnlineSession() {
        return sessionCounter;
    }

} 
