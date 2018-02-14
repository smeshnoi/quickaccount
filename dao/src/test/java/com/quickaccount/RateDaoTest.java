package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RateDaoTest {

    @Before
    public void initDb() {
        ConnectionManager.openSessionFactory();
    }

    @Test
    public void getRateByUser() {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("USD");
        Currency currency2 = new Currency("EUR");
        CurrencyDao.getInstance().save(currency);
        CurrencyDao.getInstance().save(currency2);
        Currency currency3 = new Currency("BYN");
        Currency currency4 = new Currency("RUB");
        CurrencyDao.getInstance().save(currency3);
        CurrencyDao.getInstance().save(currency4);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        UserDao.getInstance().save(user);
        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(2017, 12, 25);
        LocalDate date3 = LocalDate.of(2017, 11, 11);
        Rate rate = new Rate(date, currency, currency2, 1.251, user);
        RateDao.getInstance().save(rate);
        Rate rate2 = new Rate(date2, currency3, currency4, 1.248, user);
        RateDao.getInstance().save(rate2);
        List<Rate> ratesByUser = RateDao.getInstance().getRatesByUser(user);
        assertThat(ratesByUser.get(0).getRate(), equalTo(1.251));

        transaction.commit();
        session.close();
    }

    @After
    public void closeDB() {
        ConnectionManager.getSessionFactory().close();
    }
}
