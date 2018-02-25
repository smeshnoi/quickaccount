package controller;

import com.quickaccount.entity.Account;
import com.quickaccount.repository.AccountRepository;
import com.quickaccount.repository.TypeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Array;
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

    @ModelAttribute("pageCount")
    public int[] startPages() {
        int count = 0;
        count = accountRepository.countAllByAccountNameContainingAndTypeAccountTypeDC("", null);
        int[] pageArray = new int[count];
        for (int i = 0; i < count; i++) {
            pageArray[i] = i + 1;
        }
        return pageArray;
    }

//    @ModelAttribute("size")
//    public int getSizeAccountList() {
//        int count = 0;
//        //count = accountRepository.countAllByAccountNameContainingAndTypeAccountTypeDC()
//        return count;
//    }

    @GetMapping("/account")
    public String showAccountPage(Model model) {
        model.addAttribute("account", new Account());
        return "account";
    }

    @PostMapping("/account")
    public String showAccountPageAfterPost(Account account) {
        System.out.println(account.getAccountName());
        System.out.println(account.getTypeAccount().getTypeDC());
        return "account";
    }
}
