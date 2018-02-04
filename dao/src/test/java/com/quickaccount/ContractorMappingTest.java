package com.quickaccount;

import com.quickaccount.entity.Address;
import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.ContractorIndividual;
import com.quickaccount.entity.ContractorLegalEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ContractorMappingTest {
    private static ContractorMappingTest INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public ContractorMappingTest() {
    }

    public static ContractorMappingTest getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (ContractorMappingTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ContractorMappingTest();
                }
            }
        }
        return INSTANCE;
    }

    @Test
    public void testAddContractor() {
        UserMappingTest.getINSTANCE().testAddUser();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
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
        System.out.println(contractorLegalEntity.getId() + " " + address.getId());
        contractorLegalEntity.setAddress(address);
        session.save(contractorLegalEntity);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetContractor() {
        testAddContractor();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("select c from Contractor c", Contractor.class)
                .list()
                .forEach(System.out::println);
        transaction.commit();
        session.close();
    }
}
