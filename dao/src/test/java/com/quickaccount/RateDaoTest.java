package com.quickaccount;

import com.quickaccount.entity.*;
import config.TestDatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class RateDaoTest {

    @Autowired
    private CurrencyDaoImpl currencyDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RateDaoImpl rateDao;


    @Test
    public void getRateByUser() {
        Currency currency = new Currency("USD");
        Currency currency2 = new Currency("EUR");
        currencyDao.save(currency);
        currencyDao.save(currency2);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        userDao.save(user);
        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(2017, 12, 25);
        LocalDate date3 = LocalDate.of(2017, 11, 11);
        Rate rate = new Rate(date, currency, currency2, 1.251, user);
        rateDao.save(rate);
        Rate rate2 = new Rate(date2, currency, currency2, 1.248, user);
        rateDao.save(rate2);
        Rate rate3 = new Rate(date3, currency, currency2, 1.24, user);
        rateDao.save(rate3);
        List<Rate> ratesByUser = rateDao.getRatesByUser(user);
        assertThat(ratesByUser.get(0).getRate(), equalTo(1.251));
    }
}
