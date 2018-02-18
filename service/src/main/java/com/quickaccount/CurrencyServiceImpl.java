package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyDao currencyDao;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public Currency getCurrency() {
        return (Currency) currencyDao.findById(1L);
    }

    public List<Currency> getAll() {
        return currencyDao.findAll();
    }
}
