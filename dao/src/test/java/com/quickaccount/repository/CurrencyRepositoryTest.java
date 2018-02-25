package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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
        List<Currency> all = currencyRepository.findAll();//.forEach(System.out::println);
        assertThat(all.size(), equalTo(2));
    }
}
