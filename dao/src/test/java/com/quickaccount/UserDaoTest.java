package com.quickaccount;

import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UserDaoTest {
    private SessionFactory sessionFactory;

//    @Before
//    public void initDb() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//    }

    @Test
    public void getUserById() {
                User user = new User("test","Test", "Testov",
                new Currency("USD"), "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        UserDao.getInstance().save(user);

    }
}
