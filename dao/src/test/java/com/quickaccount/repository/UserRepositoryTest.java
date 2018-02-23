package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void saveUser() {
        Currency currency = new Currency("USD");
        Currency save = currencyRepository.save(currency);
        User user = new User("test","Test", "Testov",
                save, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        userRepository.save(user);
    }

    @Test
    public void getUserByLogin() {
        Currency currency = new Currency("USD");
        Currency save = currencyRepository.save(currency);
        User user = new User("test","Test", "Testov",
                save, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        userRepository.save(user);
        assertThat(userRepository.getUserByLogin("test").getLogin(), equalTo("test"));
    }
}
