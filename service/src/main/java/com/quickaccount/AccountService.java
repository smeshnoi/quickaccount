package com.quickaccount;

import com.quickaccount.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    List<Account> getAllByParameter(String findAccount, int limitPage, int page, String typeAccount);
}
