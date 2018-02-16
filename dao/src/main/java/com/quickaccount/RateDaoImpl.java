package com.quickaccount;

import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RateDaoImpl extends BaseDaoImpl<Rate> implements RateDao {

    @Override
    public List<Rate> getRatesByUser(User user) {
        Session session = getSessionFactory().getCurrentSession();
        List<Rate> rateList = null;
        System.out.println(user.getId());
        rateList = session.createQuery("select r from Rate r "
                + "where r.userRate.id = :id ", Rate.class)
                .setParameter("id", user.getId())
                .getResultList();
        return rateList;
    }
}
