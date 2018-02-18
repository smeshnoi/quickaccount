//package com.quickaccount;
//
//import com.quickaccount.connection.ConnectionManager;
//import com.quickaccount.entity.*;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
//
//public class RateDaoTest {
//
//    @Before
//    public void initDb() {
//        ConnectionManager.openSessionFactory();
//    }
//
//    @Test
//    public void getRateByUser() {
//        Session session = ConnectionManager.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Currency currency = new Currency("USD");
//        Currency currency2 = new Currency("EUR");
//        CurrencyDaoImpl.getInstance().save(currency);
//        CurrencyDaoImpl.getInstance().save(currency2);
//        User user = new User("test","Test", "Testov",
//                currency, "passw", Role.USER,
//                new Contact("testA@gmail.com", "+375296465656"));
//        UserDaoImpl.getInstance().save(user);
//        LocalDate date = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2017, 12, 25);
//        LocalDate date3 = LocalDate.of(2017, 11, 11);
//        Rate rate = new Rate(date, currency, currency2, 1.251, user);
//        RateDaoImpl.getInstance().save(rate);
//        Rate rate2 = new Rate(date2, currency, currency2, 1.248, user);
//        RateDaoImpl.getInstance().save(rate2);
//        Rate rate3 = new Rate(date3, currency, currency2, 1.24, user);
//        RateDaoImpl.getInstance().save(rate3);
//        List<Rate> ratesByUser = RateDaoImpl.getInstance().getRatesByUser(user);
//        assertThat(ratesByUser.get(0).getRate(), equalTo(1.251));
//
//        transaction.commit();
//        session.close();
//    }
//
//    @After
//    public void closeDB() {
//        ConnectionManager.getSessionFactory().close();
//    }
//}
