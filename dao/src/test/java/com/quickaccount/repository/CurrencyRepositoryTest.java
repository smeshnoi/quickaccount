package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void getAllCurrency () {
        Currency currency = new Currency("USD");
        Currency currency2 = new Currency("EUR");
        currencyRepository.save(currency);
        currencyRepository.save(currency2);
        currencyRepository.findAll().forEach(System.out::println);
    }
}
