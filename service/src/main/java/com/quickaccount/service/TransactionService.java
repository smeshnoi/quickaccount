package com.quickaccount.service;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.Company;
import com.quickaccount.entity.TransactionAccount;
import com.quickaccount.entity.User;

import java.util.List;

public interface TransactionService {
    List<TransactionAccount> findAllByCompany(Company company);

    TransactionAccount addTransacton(TransactionDto transactionDto, User userbyLogin);

    List<TransactionAccount> findAllByUser(User user);
}
