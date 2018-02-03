package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class CurrencyMappingTest {
    private static CurrencyMappingTest INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public CurrencyMappingTest() {
    }

    public static CurrencyMappingTest getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (CurrencyMappingTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CurrencyMappingTest();
                }
            }
        }
        return INSTANCE;
    }

    @Test
    public void testAddDB () {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("HUN");
        Currency currency2 = new Currency("USD");
        session.save(currency);
        session.save(currency2);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetDB () {
        Session session = SESSION_FACTORY.openSession();
        CurrencyMappingTest.this.testAddDB();
        Transaction transaction = session.beginTransaction();
        session.createQuery("select c from Currency c", Currency.class).list()
        .forEach(System.out::println);

        transaction.commit();
        session.close();
    }
}
