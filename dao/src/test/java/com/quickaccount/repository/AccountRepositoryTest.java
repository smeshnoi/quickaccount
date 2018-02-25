package com.quickaccount.repository;

import com.quickaccount.config.PersistenceConfig;
import com.quickaccount.entity.Account;
import com.quickaccount.entity.Contact;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Role;
import com.quickaccount.entity.TypeAccount;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class AccountRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeAccountRepository typeAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void getAccountTest() {
        Currency currency = new Currency("USD");
        Currency currency2 = new Currency("EUR");
        currencyRepository.save(currency);
        currencyRepository.save(currency2);
        User user = new User("test","Test", "Testov",
                currency, "passw", Role.USER,
                new Contact("testA@gmail.com", "+375296465656"));
        userRepository.save(user);
        TypeAccount typeAccount = new TypeAccount("Test Type", TypeDC.CREDIT);
        typeAccountRepository.save(typeAccount);
        Account account = new Account("Test Account", typeAccount, user);
        accountRepository.save(account);
        Account account2 = new Account("Test2", typeAccount, user);
        accountRepository.save(account2);
        Account account3 = new Account("Test3", typeAccount, user);
        accountRepository.save(account3);
        Account account4 = new Account("Test43", typeAccount, user);
        accountRepository.save(account4);
        Account account5 = new Account("Test53", typeAccount, user);
        accountRepository.save(account5);
        Account account6 = new Account("Test63", typeAccount, user);
        accountRepository.save(account6);
        Account account7 = new Account("Test73", typeAccount, user);
        accountRepository.save(account7);
        Account account8 = new Account("Test8", typeAccount, user);
        accountRepository.save(account8);
        Integer integer = accountRepository.countAllByAccountNameContainingAndTypeAccountTypeDC("3", TypeDC.CREDIT);
        assertThat(integer, equalTo(5));
        Pageable pageable = new PageRequest(0,3);
        List<Account> listAccount = accountRepository.findAllByAccountNameContainingAndTypeAccountTypeDC("3", TypeDC.CREDIT, pageable);
        assertThat(listAccount.size(), equalTo(3));
    }
}
