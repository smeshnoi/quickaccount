package com.quickaccount.service;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount);
    List<Account> findAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC, Pageable pageable);
    int countAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC);
}
