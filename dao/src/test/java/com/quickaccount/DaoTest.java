package com.quickaccount;

import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DaoTest {
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DataImportTest.getInstance().importData(sessionFactory);
    }

    @Test
    public void testGetCurrency() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = session.get(Currency.class, 2L);
        assertThat(currency.getCurrency(), equalTo("USD"));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1L);
        assertThat(user.getLogin(), equalTo("test"));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetCompany() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company Company = session.get(Company.class, 1L);
        assertThat(Company.getCompanyName(), equalTo("Ivanov and Co"));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetCurrencyRate() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Rate rate = session.get(Rate.class, 1L);
        assertThat(rate.getCurrencyIn().getCurrency(), equalTo("HUN"));
        assertThat(rate.getCurrencyOut().getCurrency(), equalTo("USD"));
        assertThat(rate.getRate(), equalTo(10.2));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetContractor() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ContractorIndividual contractor = session.get(ContractorIndividual.class, 1L);
        assertThat(contractor.getFirstName(), equalTo("Ivan"));
        assertThat(contractor.getNumberPassport(), equalTo("V123456"));
        ContractorLegalEntity contractor2 = session.get(ContractorLegalEntity.class, 2L);
        assertThat(contractor2.getAccount(), equalTo("UK123A12434112"));
        assertThat(contractor2.getContractorName(), equalTo("Test Company"));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetAccount() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.get(Account.class, 1L);
        assertThat(account.getAccountName(), equalTo("Test Account"));
        assertThat(account.getTypeAccount().getTypeDC(), equalTo(TypeDC.CREDIT));
        assertThat(account.getUserAccount().getLastName(), equalTo("Testov"));
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetTransaction() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TransactionAccount transactionAccount = session.get(TransactionAccount.class, 1L);
        assertThat(transactionAccount.getCompany().getCompanyName(), equalTo("Ivanov and Co"));
        assertThat(transactionAccount.getAmount(), equalTo(100.55));
        assertThat(transactionAccount.getContractor().getContractorName(), equalTo("Test"));
        transaction.commit();
        session.close();
    }
}
