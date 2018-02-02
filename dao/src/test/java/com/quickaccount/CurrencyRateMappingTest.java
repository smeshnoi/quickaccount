package com.quickaccount;

import com.quickaccount.entity.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class CurrencyRateMappingTest {
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();
    @Test
    public void testAddCurrencyRate() {
        CurrencyMappingTest.getINSTANCE().testAddDB();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Rate rate = new Rate();
        transaction.commit();
        session.close();
    }
}
