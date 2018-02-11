package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AccountDaoTest {
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DataImportTest.getInstance().importData(sessionFactory);
    }

    @Test
    public void testGetAccount() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1L);
        //Account account = AccountDao.getInstance().findById(1L);
        Account account = session.get(Account.class, 1L);
        //assertThat(account.getAccountName(), equalTo("Test Account"));
        //assertThat(account.getTypeAccount().getTypeDC(), equalTo(TypeDC.CREDIT));
        //assertThat(account.getUserAccount().getLastName(), equalTo("Testov"));
        System.out.println(account);
        System.out.println(user);
        Account account2 = AccountDao.getInstance().findById(1L);
        System.out.println(account2);
        transaction.commit();
        session.close();

    }
}
