package com.quickaccount;

import com.quickaccount.entity.User;

public interface UserDao extends BaseDao<User> {
    User getUserByLogin(String login);
}
