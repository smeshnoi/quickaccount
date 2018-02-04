package com.quickaccount;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

public class CompanyMappingTest {
    private static CompanyMappingTest INSTANCE = null;
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();

    public CompanyMappingTest() {
    }

    public static CompanyMappingTest getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (CompanyMappingTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CompanyMappingTest();
                }
            }
        }
        return INSTANCE;
    }

    @Test
    public void testAddCompany() {
        UserMappingTest.getINSTANCE().testAddUser();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1L);
        Company company = new Company("Ivanov and Co", "consalting",
                new Contact("ivanov_co@gmail.com", "+38094121421"), user);
        session.save(company);
        transaction.commit();
        session.close();
    }

    @Test
    public void testGetCompany() {
        testAddCompany();
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        List<Company> companyList = session.createQuery("select c from Company c", Company.class).list();
        for (Company element: companyList) {
            System.out.println(element.getCompanyName());
        }
        transaction.commit();
        session.close();
    }
}
