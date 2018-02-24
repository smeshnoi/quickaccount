package com.quickaccount.service;

import com.quickaccount.CurrencyDao;
import com.quickaccount.entity.Currency;
import com.quickaccount.repository.CurrencyRepository;
import config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@ContextConfiguration(classes = ServiceConfig.class)
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;
    private CurrencyDao currencyDao;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public Currency getCurrency() {
        return (Currency) currencyDao.findById(1L);
    }

    public List<Currency> getAll() {
        List<Currency> all = (List<Currency>)currencyRepository.findAll();
        return all;
    }
}
