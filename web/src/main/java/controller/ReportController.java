package controller;

import com.quickaccount.entity.Company;
import com.quickaccount.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ReportController {
    private TransactionService transactionService;
    private CompanyService companyService;
    private UserService userService;
    private ContractorService contractorService;
    private CurrencyService currencyService;
    private AccountService accountService;
    private RateService rateService;

    public ReportController(TransactionService transactionService, CompanyService companyService, UserService userService, ContractorService contractorService, CurrencyService currencyService, AccountService accountService, RateService rateService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
        this.userService = userService;
        this.contractorService = contractorService;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.rateService = rateService;
    }

    @GetMapping("/report")
    public String getReportPage() {
        return "report";
    }

    @GetMapping("/contractorreport")
    public String getContractorReportPage(Model model, String dateStart, String dateEnd, Principal principal) {
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userService.getUserbyLogin(principal.getName()));
        //transactionService.findAllByTransactionDateBetweenAndCompanyInOrderByTransactionDate();
        return "contractorreport";
    }

}
