package com.quickaccount;

import com.quickaccount.entity.User;

public class UserDao extends BaseDao<User> {
    private static UserDao instance = null;

//    private static final SessionFactory SESSION_FACTORY =
//            new Configuration().configure().buildSessionFactory();

    public UserDao() {
        super(User.class);
    }

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

}
