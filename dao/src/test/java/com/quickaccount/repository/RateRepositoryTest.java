package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class RateRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RateRepository rateRepository;

    @Test
    public void getRateByUserTest () {

        Currency currency = new Currency("USD");
        Currency currency2 = new Currency("EUR");
        currencyRepository.save(currency);
        currencyRepository.save(currency2);
        User user = new User("test","Test", "Testov",
                currency, "passw",
                new Contact("testA@gmail.com", "+375296465656"));
        userRepository.save(user);
        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(2017, 12, 25);
        LocalDate date3 = LocalDate.of(2017, 11, 11);
        Rate rate = new Rate(date, currency, currency2, 1.251, user);
        rateRepository.save(rate);
        Rate rate2 = new Rate(date2, currency, currency2, 1.248, user);
        rateRepository.save(rate2);
        Rate rate3 = new Rate(date3, currency, currency2, 1.24, user);
        rateRepository.save(rate3);
        List<Rate> ratesByUser = rateRepository.getAllByUserRate(user);
        assertThat(ratesByUser.get(0).getRate(), equalTo(1.251));
    }
}
