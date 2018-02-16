package com.quickaccount;

import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getUserByLogin(String login) {
        Session session = getSessionFactory().getCurrentSession();
        User user = null;
        Query<User> query = session.createQuery("select u from User u "
                + "where u.login = :login ", User.class)
                .setParameter("login", login);
        user = query.list().get(0);
        return user;
    }

}
