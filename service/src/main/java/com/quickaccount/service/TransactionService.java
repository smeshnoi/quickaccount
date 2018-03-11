package com.quickaccount.service;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.TransactionAccount;

import java.util.List;

public interface TransactionService {
    List<TransactionAccount> findAllByCompany(Company company);
}
