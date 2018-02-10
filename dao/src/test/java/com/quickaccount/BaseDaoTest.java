package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BaseDaoTest {
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testGetCurrencyById() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("HUN");
        Currency currency2 = new Currency("USD");
        CurrencyDao.getInstance().save(currency);
        CurrencyDao.getInstance().save(currency2);
        transaction.commit();

        Transaction transaction2 = session.beginTransaction();
        Currency currency3 = CurrencyDao.getInstance().findById(1L);
        System.out.println(currency3.getCurrency());

        transaction2.commit();
        session.close();
    }

    @Test
    public void testGetCurrencyAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("EUR");
        Currency currency2 = new Currency("USD");
        CurrencyDao.getInstance().save(currency);
        CurrencyDao.getInstance().save(currency2);
        List<Currency> currencyList = CurrencyDao.getInstance().findAll();
        for (Currency curr: currencyList) {
            System.out.println(curr.getCurrency());
        }
        transaction.commit();
        session.close();
    }
}
