package controller;

import com.quickaccount.entity.Account;
import com.quickaccount.repository.AccountRepository;
import com.quickaccount.repository.TypeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AccountController {
    //private TypeAccountRepository typeAccountRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @ModelAttribute("accounts")
    public List<Account> currencies() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    @GetMapping("/account")
    public String showAccountPage() {
        return "account";
    }
}
