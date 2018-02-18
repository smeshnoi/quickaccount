package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.User;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {

    List<Account> findAll(User user);
    List<Account> findAllByParameter(String searchText, int limitPage, int page, String typeAccount);
}
