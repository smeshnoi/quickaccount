package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public User getUserByLogin(String login) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        Query<User> query = session.createQuery("select u from User u " +
                "where u.login = :login ", User.class)
                .setParameter("login", login);
        user = query.list().get(0);
        transaction.commit();
        session.close();
        return user;
    }

}
