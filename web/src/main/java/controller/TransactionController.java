package controller;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.*;
import com.quickaccount.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String showTransactionsPage(Model model, Principal principal, Integer page) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userbyLogin);
        model.addAttribute("user", userbyLogin);
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("transaction", new TransactionDto());
        if (page == null) {
            page = 0;
        } else {
            page = page - 1;
        }
        List<TransactionAccount> allByUser = transactionService.findAllByUser(allByUserCompany);
        int count = transactionService.countAllByCompanyInOrderByTransactionDate(allByUserCompany);
        Pageable pageable = new PageRequest(page, 10);
        int allPage =  (int) Math.ceil((double) count / 10);
        model.addAttribute("pageCount", getPageArray(allPage));
        model.addAttribute("transactions", transactionService.findAllByUser(allByUserCompany));
        return "transactions";
    }

    public int[] getPageArray(int count) {
        int[] pageArray = new int[count];
        for (int i = 0; i < count; i++) {
            pageArray[i] = i + 1;
        }
        return pageArray;
    }

    @PostMapping("/addtransaction")
    public String addTransaction(Model model, TransactionDto transactionDto, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        LocalDate date = LocalDate.parse(transactionDto.getTransactionDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        Currency currency = currencyService.findOne(transactionDto.getCurrency().getId());
        List<Rate> allByDate = rateService.findAllByDate(userbyLogin, date);
        model.addAttribute("transactionDto", transactionDto);
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("companies", companyService.findAllByUserCompany(userbyLogin));
        model.addAttribute("accounts", accountService.findAllByUserAccountIn(userbyLogin));
        model.addAttribute("contractors", contractorService.findContractorsByUser(userbyLogin));
        if (currency.getCurrency().equals(userbyLogin.getCurrency().getCurrency())) {
            return "addtransaction";
        }
        for (Rate rate : allByDate) {
            if (rate.getCurrencyIn().getCurrency().equals(currency.getCurrency())) {
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
        return "redirect:transactions";
    }

    @GetMapping("/deletetransaction/{id}")
    public String showAddTransactionPage(Model model, TransactionAccount transaction, @PathVariable("id") Long id) {
        TransactionAccount transactionById = transactionService.findTransactionById(id);
        model.addAttribute("transaction", transactionById);
        transactionService.delete(transactionById);
        return "redirect:/transactions";
    }

    @PostMapping("/deletetransaction")
    public String deleteTransaction(TransactionAccount transaction) {
        System.out.println(transaction);
        return "redirect:transactions";
    }
}
