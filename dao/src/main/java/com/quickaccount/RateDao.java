package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RateDao extends BaseDao<Rate> {
    private static RateDao instance = null;

    public RateDao() {
        super(Rate.class);
    }

    public static RateDao getInstance() {
        if (instance == null) {
            synchronized (RateDao.class) {
                if (instance == null) {
                    instance = new RateDao();
                }
            }
        }
        return instance;
    }

    public List<Rate> getRatesByUser(User user) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Rate> rateList = null;
        System.out.println(user.getId());
        rateList = session.createQuery("select r from Rate r " +
                "where r.userRate.id = :id ", Rate.class)
                .setParameter("id", user.getId())
                .getResultList();

        transaction.commit();
        session.close();
        return rateList;
    }
}
