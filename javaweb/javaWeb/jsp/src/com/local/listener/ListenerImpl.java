package com.local.listener;

import jakarta.servlet.ServletContextListener;

/**
 * @author µ³
 * @version 1.0
 * 2022/4/20   21:38
 */
public class ListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("ListenerImpl.contextInitialized");
    }

    @Override
    public void contextDestroyed(jakarta.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("ListenerImpl.contextDestroyed");
    }

}
