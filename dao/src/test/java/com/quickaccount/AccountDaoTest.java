package com.quickaccount;

import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

public class AccountDaoTest {
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testGetAccount() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency2 = new Currency("USD");
        CurrencyDao.getInstance().save(currency2);
        User user = new User("test","Test", "Testov",
                currency2, "passw", Role.USER,
                new Contact("test@gmail.com", "+375296465656"));
        UserDao.getInstance().save(user);
        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        session.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        AccountDao.getInstance().save(account);

        transaction.commit();
        session.close();

    }
}
