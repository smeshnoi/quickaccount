package com.quickaccount;

import com.quickaccount.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class DataImportTest {
    private static DataImportTest INSTANCE;

    private DataImportTest() {
    }

    public static DataImportTest getInstance() {
        if (INSTANCE == null) {
            synchronized (DataImportTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataImportTest();
                }
            }
        }
        return INSTANCE;
    }

    public void importData(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Currency currency = new Currency("HUN");
        Currency currency2 = new Currency("USD");
        session.save(currency);
        session.save(currency2);
        User user = new User("test","Test", "Testov",
                currency2, "passw", Role.USER,
                new Contact("test@gmail.com", "+375296465656"));
        session.save(user);
        Company company = new Company("Ivanov and Co", "consalting",
                new Contact("ivanov_co@gmail.com", "+38094121421"), user);
        session.save(company);
        LocalDate date = LocalDate.now();
        Currency currencyIn = session.get(Currency.class, 1L);
        Currency currencyOut = session.get(Currency.class, 2L);
        Rate rate = new Rate(date, currencyIn, currencyOut, 10.2, user);
        session.save(rate);

        ContractorIndividual contractorIndividual = new ContractorIndividual();
        contractorIndividual.setContractorName("Test");
        contractorIndividual.setFirstName("Ivan");
        contractorIndividual.setLastName("Ivanov");
        contractorIndividual.setNumberPassport("V123456");
        session.save(contractorIndividual);
        ContractorLegalEntity contractorLegalEntity = new ContractorLegalEntity();
        contractorLegalEntity.setContractorName("Test Company");
        contractorLegalEntity.setAccount("UK123A12434112");
        contractorLegalEntity.setUnn("1901291119");
        session.save(contractorLegalEntity);
        Address address = new Address(2L,"Minsk", "Pobediteley", "5a", 11);
        session.save(address);
        contractorLegalEntity.setAddress(address);
        session.save(contractorLegalEntity);

        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        session.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        session.save(account);

        TransactionAccount transactionAccount =
                new TransactionAccount(date, company, account,
                        TypeDC.CREDIT, currency, 100.55, contractorIndividual);
        session.save(transactionAccount);

        transaction.commit();
        session.close();
    }
}
