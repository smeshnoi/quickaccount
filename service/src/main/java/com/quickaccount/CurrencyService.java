package com.quickaccount;

import com.quickaccount.entity.Currency;

import java.util.List;

public class CurrencyService {
    private static CurrencyService instance = null;

    public CurrencyService() {
    }

    public static CurrencyService getInstance() {
        if (instance == null) {
            synchronized (CurrencyService.class) {
                if (instance == null) {
                    instance = new CurrencyService();
                }
            }
        }
        return instance;
    }

    public Currency getCurrency() {
        return CurrencyDaoImpl.getInstance().findById(1L);
    }

    public List<Currency> getAll() {
        return CurrencyDaoImpl.getInstance().findAll();
    }
}
