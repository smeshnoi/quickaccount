package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDao extends BaseDao<Currency> {
    private static CurrencyDao instance = null;

    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public CurrencyDao() {
        super(Currency.class);
    }

    public static CurrencyDao getInstance() {
        if (instance == null) {
            synchronized (CurrencyDao.class) {
                if (instance == null) {
                    instance = new CurrencyDao();
                }
            }
        }
        return instance;
    }

    public List<Currency> getAllCurrency() {
        List<Currency> currencyList = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        currencyList = session.createQuery("select c from Currency c", Currency.class).list();
        for (Currency currency: currencyList) {
            System.out.println(currency.getCurrency());
        }
        transaction.commit();
        session.close();
        return currencyList;
    }
}
