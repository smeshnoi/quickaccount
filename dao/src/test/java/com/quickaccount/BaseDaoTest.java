package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.Currency;
import config.TestDatabaseConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class BaseDaoTest {

    @Autowired
    private CurrencyDaoImpl currencyDao;



    @Test
    public void testGetCurrencyById() {
        Currency currency = new Currency("HUN");
        Currency currency2 = new Currency("USD");
        currencyDao.save(currency);
        currencyDao.save(currency2);
        Currency currency3 = currencyDao.findById(1L);
        System.out.println(currency3.getCurrency());
        assertThat(currency3.getCurrency(), equalTo("HUN"));

    }

    @Test
    public void testGetCurrencyAll() {
        Currency currency = new Currency("EUR");
        Currency currency2 = new Currency("USD");
        currencyDao.save(currency);
        currencyDao.save(currency2);
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency curr: currencyList) {
            System.out.println(curr.getCurrency());
        }
        assertThat(currencyList.get(0).getCurrency(), equalTo("EUR"));
    }

    @Test
    public void testDelete() {
        Currency currency =  new Currency("BYN");
        Currency currency2 = new Currency("USD");
        currencyDao.save(currency);
        currencyDao.save(currency2);
        List<Currency> currencyList = currencyDao.findAll();
        assertThat(currencyList.size(), equalTo(2));
        Currency currencyDelete = currencyList.get(0);
        Long delete = currencyDao.delete(currencyDelete);
        currencyList = currencyDao.findAll();
        assertThat(currencyList.size(), equalTo(1));

    }

    @Test
    public void testUpdate() {
        Currency currency =  new Currency("BYN");
        currencyDao.save(currency);
        Currency currency2 = currencyDao.findById(1L);
        //assertThat(currency2.getCurrency(), equalTo("BYN"));
        currency2.setCurrency("EUR");
        currencyDao.update(currency2);
        Currency currency3 = currencyDao.findById(1L);
        assertThat(currency3.getCurrency(), equalTo("EUR"));
    }

}
