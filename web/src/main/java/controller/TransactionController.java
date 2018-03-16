package controller;

import com.quickaccount.service.CompanyService;
import com.quickaccount.service.ContractorService;
import com.quickaccount.service.CurrencyService;
import com.quickaccount.service.TransactionService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {
    private TransactionService transactionService;
    private CompanyService companyService;
    private UserService userService;
    private ContractorService contractorService;
    private CurrencyService currencyService;

    @Autowired
    public TransactionController(TransactionService transactionService, CompanyService companyService, UserService userService, ContractorService contractorService, CurrencyService currencyService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
        this.userService = userService;
        this.contractorService = contractorService;
        this.currencyService = currencyService;
    }
}
