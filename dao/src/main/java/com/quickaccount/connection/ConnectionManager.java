package com.quickaccount.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private ConnectionManager() {}

    public static void openSessionFactory() {
        sessionFactory = new Configuration().configure("hibernate2.cfg.xml").buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
