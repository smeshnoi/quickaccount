package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class CurrencyMappingTest {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    @Test
    public void testGetDB () {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("select c from Currency c", Currency.class).list().forEach(System.out::println);

        transaction.commit();
        session.close();
    }
}
