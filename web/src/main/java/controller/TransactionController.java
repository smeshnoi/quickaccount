package controller;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.User;
import com.quickaccount.service.AccountService;
import com.quickaccount.service.CompanyService;
import com.quickaccount.service.ContractorService;
import com.quickaccount.service.CurrencyService;
import com.quickaccount.service.TransactionService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TransactionController {
    private TransactionService transactionService;
    private CompanyService companyService;
    private UserService userService;
    private ContractorService contractorService;
    private CurrencyService currencyService;
    private AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, CompanyService companyService,
                                 UserService userService, ContractorService contractorService,
                                 CurrencyService currencyService, AccountService accountService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
        this.userService = userService;
        this.contractorService = contractorService;
        this.currencyService = currencyService;
        this.accountService = accountService;
    }

    @GetMapping("/transactions")
    public String showTransactionsPage(Model model, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        model.addAttribute("user", userbyLogin);
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("transaction", new TransactionDto());
        return "transactions";
    }

    @PostMapping("/addtransaction")
    public String addTransaction(Model model, TransactionDto transactionDto, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        model.addAttribute("transactionDto", transactionDto);
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("companies", companyService.findAllByUserCompany(userbyLogin));
        model.addAttribute("accounts", accountService.getAllAccounts(userbyLogin));
        model.addAttribute("contractors", contractorService.findContractorsByUser(userbyLogin));
        return "addtransaction";
    }

//    @GetMapping("/addtransaction")
//    public String showAddTransactionPage() {
//        return "addtransaction";
//    }
}
