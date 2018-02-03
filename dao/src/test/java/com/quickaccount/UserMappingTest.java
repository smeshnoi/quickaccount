package com.quickaccount;

import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

public class UserMappingTest {
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    @Test
    public void testAddUser() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("GBR");
        //public User(String login, String first_name, String last_name,
        // Currency currency, String password, Role role,
        // Contact contact) {
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("test@gmail.com", "+375296465656"));
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetUser() {
        testAddUser();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("select u from User u", User.class).list();
        for (User user: userList) {
            System.out.println(user.getFirst_name() + ": " + user.getContact().getEmail() + " - "
                    + user.getCurrency().getCurrency());
        }
        transaction.commit();
        session.close();
    }
}