package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
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
        currencyRepository.findAll().forEach(System.out::println);
    }
}
