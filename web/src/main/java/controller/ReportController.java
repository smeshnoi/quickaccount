package controller;

import com.quickaccount.dto.ReportToUserCurrencyDto;
import com.quickaccount.entity.Company;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.TransactionAccount;
import com.quickaccount.entity.User;
import com.quickaccount.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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
        LocalDate dateS = LocalDate.parse(dateStart);
        LocalDate dateE = LocalDate.parse(dateEnd);
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userService.getUserbyLogin(principal.getName()));
        List<TransactionAccount> transactionAccountList = transactionService.findAllByTransactionDateBetweenAndCompanyInOrderByTransactionDate(dateS, dateE, allByUserCompany);
        model.addAttribute("transactions", transactionAccountList);
        return "contractorreport";
    }

    @GetMapping("/usercurrencyreport")
    public String showUserCurrencyTransactionReportPage(Model model, String dateStart, String dateEnd, Principal principal) {
        if (dateStart == null) {
            dateStart = LocalDate.now().minusYears(1).toString();
        }
        if (dateEnd == null) {
            dateEnd = LocalDate.now().toString();
        }
        LocalDate dateS = LocalDate.parse(dateStart);
        LocalDate dateE = LocalDate.parse(dateEnd);
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        List<ReportToUserCurrencyDto> reportByUserCurrency = new ArrayList<>();
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userbyLogin);
        if (dateS.equals(null) || dateE.equals(null)) {
            List<TransactionAccount> allByUser = transactionService.findAllByUser(allByUserCompany);
            reportByUserCurrency = getReportByUserCurrency(allByUser, userbyLogin);
        } else {
            List<TransactionAccount> allByUser = transactionService.findAllByTransactionDateBetweenAndCompanyInOrderByTransactionDate(dateS, dateE, allByUserCompany);
            reportByUserCurrency = getReportByUserCurrency(allByUser, userbyLogin);
        }
        model.addAttribute("transactionsreport", reportByUserCurrency);
        return "contractorreport";
    }

    @GetMapping("/accountbalancesreport")
    public String showAccBalancesReport(Model model, Principal principal) {
        int year = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        List<ReportToUserCurrencyDto> reportByUserCurrency = new ArrayList<>();
        List<Company> allByUserCompany = companyService.findAllByUserCompany(userbyLogin);
        List<TransactionAccount> allByUser = transactionService.findAllByTransactionDateBetweenAndCompanyInOrderByTransactionDate(startDate, endDate, allByUserCompany);
        reportByUserCurrency = getReportByUserCurrency(allByUser, userbyLogin);
        reportAccountBalancesForYear(reportByUserCurrency);
        model.addAttribute("transactionsreport", reportByUserCurrency);
        return "accountbalancesreport";
    }

    public List<ReportToUserCurrencyDto> getReportByUserCurrency(List<TransactionAccount> allByUser, User userbyLogin) {
        List<ReportToUserCurrencyDto> reportDtoList = new ArrayList<>();
        List<Rate> listRate = rateService.findAllByUser(userbyLogin);
        for (TransactionAccount transaction: allByUser) {
            Double amountDebit = transaction.getAmountDebit();
            Double amountCredit = transaction.getAmountCredit();
            Long transactionCurrencyIdNew = transaction.getCurrency().getId();
            for (Rate currencyRate: listRate) {
                    if (currencyRate.getRateDate().equals(transaction.getTransactionDate())) {
                        if (currencyRate.getCurrencyIn().getId() == transaction.getCurrency().getId()) {
                            amountDebit = transaction.getAmountDebit() * currencyRate.getRate();
                            amountCredit = transaction.getAmountDebit() * currencyRate.getRate();
                        }
                    }
                }
            ReportToUserCurrencyDto report = new ReportToUserCurrencyDto(transaction.getId(), transaction.getTransactionDate(),
                    amountDebit, transaction.getAccountDebit(),
                    amountCredit, transaction.getAccountCredit(),
                    transaction.getCompany(), transaction.getContractor());
            reportDtoList.add(report);
        }
        return reportDtoList;
    }

    private void reportAccountBalancesForYear(List<ReportToUserCurrencyDto> reportByUserCurrency) {

    }

}
