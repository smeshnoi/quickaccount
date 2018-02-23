package com.quickaccount.repository;

import com.quickaccount.entity.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
