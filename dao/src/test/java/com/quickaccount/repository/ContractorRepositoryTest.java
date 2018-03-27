package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class ContractorRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Test
    public void getContractorTest() {
        Currency currency = new Currency("CCC");
        currencyRepository.save(currency);

        User user = new User("testContr","Test", "Testov",
                currency, "passw",
                new Contact("testContr@gmail.com", "+375296465656"));
        userRepository.save(user);

    }
}
