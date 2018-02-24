package com.quickaccount.service;

import com.quickaccount.AccountDao;
import com.quickaccount.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

//    @Autowired
//    public AccountServiceImpl(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

    @Override
    public List<Account> getAll() {
        System.out.println(accountDao.findAll());
        return accountDao.findAll();
    }

    @Override
    public List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount) {
        return accountDao.findAllByParameter(findAccount, limitPage, page, typeAccount);
    }
}
