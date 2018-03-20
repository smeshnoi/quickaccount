package controller;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.*;
import com.quickaccount.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
public class TransactionController {
    private TransactionService transactionService;
    private CompanyService companyService;
    private UserService userService;
    private ContractorService contractorService;
    private CurrencyService currencyService;
    private AccountService accountService;
    private RateService rateService;

    @Autowired
    public TransactionController(TransactionService transactionService, CompanyService companyService,
                                 UserService userService, ContractorService contractorService,
                                 CurrencyService currencyService, AccountService accountService,
                                 RateService rateService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
        this.userService = userService;
        this.contractorService = contractorService;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.rateService = rateService;
    }

    @GetMapping("/transactions")
    public String showTransactionsPage(Model model, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userbyLogin);
        model.addAttribute("user", userbyLogin);
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("transaction", new TransactionDto());
        List<TransactionAccount> allByUser = transactionService.findAllByUser(allByUserCompany);
        for (TransactionAccount transaction : allByUser) {
            System.out.println(transaction.getTransactionDate());
        }
        model.addAttribute("transactions", transactionService.findAllByUser(allByUserCompany));
        return "transactions";
    }

    @PostMapping("/addtransaction")
    public String addTransaction(Model model, TransactionDto transactionDto, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        LocalDate date = LocalDate.parse(transactionDto.getTransactionDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        Currency currency = currencyService.findOne(transactionDto.getCurrency().getId());
        List<Rate> allByDate = rateService.findAllByDate(userbyLogin, date);
        for (Rate rate : allByDate) {
            if (rate.getCurrencyIn().getCurrency().equals(currency.getCurrency())) {
                model.addAttribute("transactionDto", transactionDto);
                model.addAttribute("currencies", currencyService.findAll());
                model.addAttribute("companies", companyService.findAllByUserCompany(userbyLogin));
                model.addAttribute("accounts", accountService.getAllAccounts(userbyLogin));
                model.addAttribute("contractors", contractorService.findContractorsByUser(userbyLogin));
                return "addtransaction";
            }
        }
        //if (userbyLogin.getCurrency().getCurrency() == transactionDto.getCurrency().getCurrency())

//        model.addAttribute("transactionDto", transactionDto);
//        model.addAttribute("currencies", currencyService.findAll());
//        model.addAttribute("companies", companyService.findAllByUserCompany(userbyLogin));
//        model.addAttribute("accounts", accountService.getAllAccounts(userbyLogin));
//        model.addAttribute("contractors", contractorService.findContractorsByUser(userbyLogin));
        return "redirect:addrate";
    }

    @PostMapping("/transactions")
    public String addTransactionFull(Model model, TransactionDto transactionDto, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        transactionService.addTransacton(transactionDto, userbyLogin);
//        model.addAttribute("user", userbyLogin);
//        model.addAttribute("currencies", currencyService.findAll());
//        model.addAttribute("transaction", new TransactionDto());
        return "redirect:transactions";
    }

//    @GetMapping("/addtransaction")
//    public String showAddTransactionPage() {
//        return "addtransaction";
//    }
}
