package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

//    public AccountDaoImpl() {
//        super(Account.class);
//    }

    @Override
    public List<Account> findAll(User user) {
        Session session = getSessionFactory().openSession();
        List<Account> accountList = session.createQuery("select a from Account a", Account.class).list();
        session.close();
        return super.findAll();
    }

    @Override
    public List<Account> findAllByParameter(String searchText, int limitPage, int page, String typeAccount) {
        Session session = getSessionFactory().openSession();
        TypeDC typeAccDC = null;
        if ("CREDIT".equals(typeAccount)) {
            typeAccDC = TypeDC.CREDIT;
        } else if ("DEBIT".equals(typeAccount)) {
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
        session.close();
        return accountList;
    }
}
