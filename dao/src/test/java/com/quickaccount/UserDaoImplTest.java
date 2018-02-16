package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class UserDaoImplTest {

    @Before
    public void initDb() {
        ConnectionManager.openSessionFactory();
    }

    @Test
    public void getUserByLogin() {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("USD");
        CurrencyDaoImpl.getInstance().save(currency);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        UserDaoImpl.getInstance().save(user);
        User user2 = new User("user","Test1", "Testov1",
                currency, "passw", Role.USER,
                new Contact("testuser@gmail.com", "+3752961111156"));
        UserDaoImpl.getInstance().save(user2);
        assertThat(UserDaoImpl.getInstance().getUserByLogin("test").getLogin(), equalTo("test"));

        transaction.commit();
        session.close();
    }

    @After
    public void closeDB() {
        ConnectionManager.getSessionFactory().close();
    }
}
