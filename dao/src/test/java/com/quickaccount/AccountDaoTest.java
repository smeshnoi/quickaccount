package com.quickaccount;

import com.quickaccount.entity.*;
import config.TestDatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class AccountDaoTest {
    @Autowired
    private AccountDaoImpl accountDao;

    @Autowired
    private CurrencyDaoImpl currencyDao;

    @Autowired
    private TypeAccountDaoImpl typeAccountDao;

    @Autowired
    private UserDaoImpl userDao;


    @Test
    public void testGetAccount() {
        Currency currency = new Currency("USD");
        currencyDao.save(currency);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("test@gmail.com", "+375296465656"));
        userDao.save(user);

        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        typeAccountDao.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        accountDao.save(account);
        Account account2 = new Account("Test2", typeAccount, user);
        accountDao.save(account2);
        Account account3 = new Account("Test3", typeAccount, user);
        accountDao.save(account3);
        Account account4 = new Account("Test43", typeAccount, user);
        accountDao.save(account4);
        Account account5 = new Account("Test53", typeAccount, user);
        accountDao.save(account5);
        Account account6 = new Account("Test63", typeAccount, user);
        accountDao.save(account6);
        Account account7 = new Account("Test73", typeAccount, user);
        accountDao.save(account7);
        Account account8 = new Account("Test8", typeAccount, user);
        accountDao.save(account8);
        List<Account> allByParameter = accountDao.findAllByParameter("3", 3, 1, "CREDIT");
        System.out.println(allByParameter.size());
        assertThat(allByParameter.size(), equalTo(3));
    }
}
