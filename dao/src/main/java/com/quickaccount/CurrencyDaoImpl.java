package com.quickaccount;

import com.quickaccount.entity.Currency;

public class CurrencyDaoImpl extends BaseDaoImpl<Currency> implements CurrencyDao<Currency> {
    private static CurrencyDaoImpl instance = null;

    public CurrencyDaoImpl() {
        super(Currency.class);
    }

    public static CurrencyDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CurrencyDaoImpl.class) {
                if (instance == null) {
                    instance = new CurrencyDaoImpl();
                }
            }
        }
        return instance;
    }

}
