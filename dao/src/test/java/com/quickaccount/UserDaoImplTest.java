package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import config.TestDatabaseConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class UserDaoImplTest {
    @Autowired
    private CurrencyDaoImpl currencyDao;

    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void getUserByLogin() {
        Currency currency = new Currency("USD");
        currencyDao.save(currency);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        userDao.save(user);
        User user2 = new User("user","Test1", "Testov1",
                currency, "passw", Role.USER,
                new Contact("testuser@gmail.com", "+3752961111156"));
        userDao.save(user2);
        assertThat(userDao.getUserByLogin("test").getLogin(), equalTo("test"));
    }

}
