package com.quickaccount.service;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import com.quickaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount) {
        return null;
    }

    @Override
    public List<Account> findAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC, Pageable pageable) {
        return accountRepository.findAllByAccountNameContainingAndTypeAccountTypeDC(accountName, typeDC, pageable);
    }

    @Override
    public int countAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC) {
        return accountRepository.countAllByAccountNameContainingAndTypeAccountTypeDC(accountName, typeDC);
    }

    @Override
    public List<Account> findAllByUserAccount(User user) {
        return accountRepository.findAllByUserAccount(user);
    }

    @Override
    public Account update(Account account, Long id) {
        Account accountById = accountRepository.findById(id);
        accountById.setAccountName(account.getAccountName());
        accountById.setTypeAccount(account.getTypeAccount());
        return null;
    }
}
