package com.quickaccount;

import com.quickaccount.entity.BaseIdEntity;
import com.quickaccount.entity.Currency;

public interface CurrencyDao<C extends BaseIdEntity> extends BaseDao<Currency> {
}
