package controller;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeAccount;
import com.quickaccount.entity.TypeDC;
import com.quickaccount.entity.User;
import com.quickaccount.service.AccountService;
import com.quickaccount.service.TypeAccountService;
import com.quickaccount.service.UserService;
import com.quickaccount.service.classForms.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("accountForm")
public class AccountController {
    private AccountService accountService;

    private TypeAccountService typeAccountService;

    private UserService userService;

    @Autowired
    public AccountController(AccountService accountService, TypeAccountService typeAccountService, UserService userService) {
        this.accountService = accountService;
        this.typeAccountService = typeAccountService;
        this.userService = userService;
    }

    @ModelAttribute("account")
    public Account account() {
        return new Account();
    }

    @ModelAttribute("typeAccounts")
    public List<TypeAccount> typeAccounts() {
        return typeAccountService.findAll();
    }

    @ModelAttribute("accountForm")
    public AccountForm accountForm() {
        return new AccountForm();
    }

//    @ModelAttribute("accounts")
//    public List<Account> currencies() {
//        List<Account> all = accountService.findAllByUserAccount(null);
//        return all;
//    }

    @ModelAttribute("pageCount")
    public int[] startPages() {
        int count = 0;
        count = accountService.countAllByAccountNameContainingAndTypeAccountTypeDC("", null);
        return getPageArray(count);
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, AccountForm account, Integer page, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        List<Account> listUserAccount = accountService.findAllByUserAccount(userbyLogin);
        List<Account> accountList = accountService.findAllByUserAccount(null);
        List<Account> unionList = new ArrayList<>();
        unionList.addAll(accountList);
        unionList.addAll(listUserAccount);
        model.addAttribute("accounts", unionList);
        model.containsAttribute("accountForm");
        if (account.getLimitPage() > 0) {
            if (page == null) {
                page = 0;
            } else {
                page = page - 1;
            }
            TypeDC typeAccDC = null;
            if ("CREDIT".equals(account.getTypeAccount())) {
                typeAccDC = TypeDC.CREDIT;
            } else if ("DEBIT".equals(account.getTypeAccount())) {
                typeAccDC = TypeDC.DEBIT;
            }
            int count = accountService.countAllByAccountNameContainingAndTypeAccountTypeDC(account.getSearchText(), typeAccDC);
            int allPage =  (int) Math.ceil((double) count / account.getLimitPage());
            model.addAttribute("pageCount", getPageArray(allPage));
            Pageable pageable = new PageRequest(page, account.getLimitPage());
            //List<Account> accountList = accountService.findAllByAccountNameContainingAndTypeAccountTypeDC(account.getSearchText(), typeAccDC, pageable);
            model.addAttribute("accounts" , unionList);
        }
        return "account";
    }

    @PostMapping("/account")
    public String showAccountPageAfterPost(AccountForm account) {
        return "redirect:/account";
    }

    public int[] getPageArray(int count) {
        int[] pageArray = new int[count];
        for (int i = 0; i < count; i++) {
            pageArray[i] = i + 1;
        }
        return pageArray;
    }

    @GetMapping("/addaccount")
    public String showAddAccountPage(Model model, Account account, Integer page) {
        return "addaccount";
    }

    @PostMapping("/addaccount")
    public String addAccount(Model model, Account account, Principal principal) {
        User user = new User();
        user = userService.getUserbyLogin(principal.getName());
        System.out.println(account.getTypeAccount().getTypeAccountName());
        account.setUserAccount(user);
        accountService.save(account);
        return "account";
    }

    @GetMapping("/editaccount/{id}")
    public String showEditAccountPage(Model model, Account account, Integer page, @PathVariable("id") String id) {
        long accountId = Long.parseLong(id);
        accountService.update(account, accountId);
        return "account";
    }

}
