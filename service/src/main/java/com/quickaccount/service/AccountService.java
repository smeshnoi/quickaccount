package com.quickaccount.service;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount);
    List<Account> findAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC, Pageable pageable);
    int countAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC);
    List<Account> findAllByUserAccount(User user);
    Account update(Account account, Long id);
    Account findAccountById(Long id);
}
