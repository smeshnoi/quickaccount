package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyDaoImpl extends BaseDaoImpl<Currency> implements CurrencyDao {

    public CurrencyDaoImpl() {
        super(Currency.class);
    }

}
