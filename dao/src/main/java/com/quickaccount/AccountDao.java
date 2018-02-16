package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDao extends BaseDao<Account> {
    private static AccountDao instance = null;
//    private static final SessionFactory SESSION_FACTORY =
//            new Configuration().configure().buildSessionFactory();

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
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Account> accountList = session.createQuery("select a from Account a", Account.class).list();

        transaction.commit();
        session.close();
        return super.findAll();
    }

    public List<Account> findAllByParameter(String searchText, int limitPage, int page, String typeAccount) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        TypeDC typeAccDC = null;
        if (typeAccount.equals("CREDIT")) {
            typeAccDC = TypeDC.CREDIT;
        } else if (typeAccount.equals("DEBIT")) {
            typeAccDC = TypeDC.DEBIT;
        }
        Query<Account> query = session.createQuery("select a from Account a "
                        + "where a.accountName like :searchText "
                        + "and a.typeAccount.typeDC = :typeAccount"
                , Account.class)
                .setParameter("searchText", "%" + searchText + "%")
                .setParameter("typeAccount", typeAccDC);
        int begin = page * limitPage - limitPage;
        int end = page * limitPage;
        List<Account> accountList = query.list();
        if (begin > accountList.size()) {
            begin = accountList.size();
        }
        if (end > accountList.size()) {
            end = accountList.size();
        }
        accountList = accountList.subList(begin, end);

        transaction.commit();
        session.close();
        return accountList;
    }
}
