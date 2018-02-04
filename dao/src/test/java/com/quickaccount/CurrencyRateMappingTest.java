package com.quickaccount;

import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class CurrencyRateMappingTest {
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();
    @Test
    public void testAddCurrencyRate() {
        UserMappingTest.getINSTANCE().testAddUser();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate date = LocalDate.now();
        Currency currencyIn = session.get(Currency.class, 1L);
        Currency currencyOut = session.get(Currency.class, 2L);
        System.out.println(currencyIn + " : " + currencyOut);
        User user = session.get(User.class, 1L);
        Rate rate = new Rate(date, currencyIn, currencyOut, 10.2, user);
        session.save(rate);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetCurrencyRate() {
        testAddCurrencyRate();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(session.get(Rate.class, 1L));

        transaction.commit();
        session.close();
    }
}
