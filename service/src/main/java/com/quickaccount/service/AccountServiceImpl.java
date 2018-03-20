package com.quickaccount.service;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import com.quickaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public List<Account> findAllByUserAccountIn(List<User> userList, Pageable pageable) {
        return accountRepository.findAllByUserAccountIn(userList, pageable);
    }

    @Override
    public int countAllByUserAccountIn(List<User> userList) {
        return accountRepository.countAllByUserAccountIn(userList);
    }

    @Override
    public Account update(Account account, Long id) {
        Account accountById = accountRepository.findById(id);
        accountById.setAccountName(account.getAccountName());
        accountById.setTypeAccount(account.getTypeAccount());
        return null;
    }

    @Override
    public Account findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> getAllAccounts(User userbyLogin) {
        List<Account> listUserAccount = accountRepository.findAllByUserAccount(userbyLogin);
        List<Account> accountList = accountRepository.findAllByUserAccount(null);
        List<User> userList = new ArrayList<>();
        //userList.add(new User());
        userList.add(userbyLogin);
        //List<Account> allByUserAccountContaining = accountRepository.findAllByUserAccount(userList);
        List<Account> unionList = new ArrayList<>();
        unionList.addAll(accountList);
        unionList.addAll(listUserAccount);
        return null;// allByUserAccountContaining;
    }
}
