package com.quickaccount;

import com.quickaccount.entity.Account;

import java.util.List;

public class AccountService {
    private static AccountService instance = null;

    public AccountService() {
    }

    public static AccountService getInstance() {
        if (instance == null) {
            synchronized (AccountService.class) {
                if (instance == null) {
                    instance = new AccountService();
                }
            }
        }
        return instance;
    }

    public List<Account> getAll() {
        System.out.println(AccountDao.getInstance().findAll());
        return AccountDao.getInstance().findAll();
    }

    public List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount) {
        return AccountDao.getInstance().findAllByParameter(findAccount, limitPage, page, typeAccount);
    }
}
