package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AccountDaoTest {
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        ConnectionManager.openSessionFactory();
        //DataImportTest.getInstance().importData(sessionFactory);
    }

    @Test
    public void testGetAccount() {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("USD");
        CurrencyDaoImpl.getInstance().save(currency);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("test@gmail.com", "+375296465656"));
        UserDaoImpl.getInstance().save(user);

        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        TypeAccountDao.getInstance().save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        AccountDao.getInstance().save(account);
        Account account2 = new Account("Test2", typeAccount, user);
        AccountDao.getInstance().save(account2);
        Account account3 = new Account("Test3", typeAccount, user);
        AccountDao.getInstance().save(account3);
        Account account4 = new Account("Test43", typeAccount, user);
        AccountDao.getInstance().save(account4);
        Account account5 = new Account("Test53", typeAccount, user);
        AccountDao.getInstance().save(account5);
        Account account6 = new Account("Test63", typeAccount, user);
        AccountDao.getInstance().save(account6);
        Account account7 = new Account("Test73", typeAccount, user);
        AccountDao.getInstance().save(account7);
        Account account8 = new Account("Test8", typeAccount, user);
        AccountDao.getInstance().save(account8);
        List<Account> allByParameter = AccountDao.getInstance().findAllByParameter("3", 3, 1, "CREDIT");
        System.out.println(allByParameter.size());
        assertThat(allByParameter.size(), equalTo(3));

        transaction.commit();
        session.close();

    }

    @After
    public void closeDB() {
        ConnectionManager.getSessionFactory().close();
    }
}
