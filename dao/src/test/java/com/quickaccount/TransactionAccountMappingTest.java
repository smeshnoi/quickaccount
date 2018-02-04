package com.quickaccount;

import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class TransactionAccountMappingTest {
    private static TransactionAccountMappingTest INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public TransactionAccountMappingTest() {
    }

    public static TransactionAccountMappingTest getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (TransactionAccountMappingTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TransactionAccountMappingTest();
                }
            }
        }
        return INSTANCE;
    }

    @Test
    public void testAddTransaction() {
        ContractorMappingTest.getINSTANCE().testAddContractor();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate date = LocalDate.now();
        User user = session.get(User.class, 1L);
        Company company = new Company("Ivanov and Co", "consalting",
                new Contact("ivanov_co@gmail.com", "+38094121421"), user);
        session.save(company);
        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        session.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        session.save(account);
        Currency currency = session.get(Currency.class, 1L);
        Contractor contractor = session.get(Contractor.class, 2L);
        TransactionAccount transactionAccount = new TransactionAccount(date, company, account, TypeDC.CREDIT, currency, 100.55, contractor);
        session.save(transactionAccount);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetTransaction() {
        testAddTransaction();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("select t from TransactionAccount t", TransactionAccount.class)
                .list()
                .forEach(System.out::println);
        transaction.commit();
        session.close();
    }
}
