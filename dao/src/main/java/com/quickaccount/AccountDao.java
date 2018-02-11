package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

        List<Account> accountList = session.createQuery("select a from Account a", Account.class).list();

        transaction.commit();
        session.close();
        return super.findAll();
    }

    public List<Account> findAllByParameter(String searchText, int limitPage, int page, String typeAccount) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(searchText);
        Query<Account> query = session.createQuery("select a from Account a " +
                "where a.accountName like :searchText "
                //"and a.typeAccount = :typeAccount"
                , Account.class)
                .setParameter("searchText", "%" + searchText + "%");
                //.setParameter("typeAccount", TypeDC.valueOf(typeAccount));
        int begin = page*limitPage - limitPage;
        int end = page*limitPage;
        List<Account> accountList = query.list();
        if (end > accountList.size()) {
            end = accountList.size();
        }
        accountList = accountList.subList(begin, end);
        System.out.println(accountList);

        transaction.commit();
        session.close();
        return accountList;
    }
}
