package com.quickaccount;

import com.quickaccount.entity.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {
    private static CurrencyDao INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public CurrencyDao() {
    }

    public static CurrencyDao getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (CurrencyDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CurrencyDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Currency> getAllCurrency() {
        List<Currency> currencyList = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        currencyList = session.createQuery("select c from Currency c", Currency.class).list();
        transaction.commit();
        session.close();
        return currencyList;
    }
}
