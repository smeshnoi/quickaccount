package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AccountDao extends BaseDao<Account> {
    private static AccountDao instance = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public static AccountDao getInstance() {
        if (instance == null) {
            synchronized (AccountDao.class) {
                if (instance == null) {
                    instance = new AccountDao();
                }
            }
        }
        return instance;
    }

    public AccountDao() {
        super(Account.class);
    }

    public List<Account> findAll(User user) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();



        transaction.commit();
        session.close();
        return super.findAll();
    }
}
