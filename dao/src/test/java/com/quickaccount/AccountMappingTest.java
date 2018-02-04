package com.quickaccount;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeAccount;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class AccountMappingTest {
    private static AccountMappingTest INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public AccountMappingTest() {
    }

    public static AccountMappingTest getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (AccountMappingTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AccountMappingTest();
                }
            }
        }
        return INSTANCE;
    }

    @Test
    public void testAddAccount() {
        UserMappingTest.getINSTANCE().testAddUser();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1L);
        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        session.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        session.save(account);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetAccount() {
        testAddAccount();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.get(Account.class, 1L);
        System.out.println(account.getAccountName() + " : " + account.getTypeAccount().getTypeDC());
        transaction.commit();
        session.close();
    }
}
