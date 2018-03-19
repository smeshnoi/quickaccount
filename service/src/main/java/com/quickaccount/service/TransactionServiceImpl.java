package com.quickaccount.service;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.*;
import com.quickaccount.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private RateRepository rateRepository;
    private CurrencyRepository currencyRepository;
    private AccountRepository accountRepository;
    private ContractorRepository contractorRepository;
    private CompanyRepository companyRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, RateRepository rateRepository, CurrencyRepository currencyRepository, AccountRepository accountRepository, ContractorRepository contractorRepository, CompanyRepository companyRepository) {
        this.transactionRepository = transactionRepository;
        this.rateRepository = rateRepository;
        this.currencyRepository = currencyRepository;
        this.accountRepository = accountRepository;
        this.contractorRepository = contractorRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<TransactionAccount> findAllByCompany(Company company) {
        return null;
    }

    @Override
    public TransactionAccount addTransacton(TransactionDto transactionDto, User userbyLogin) {
        LocalDate date = LocalDate.parse(transactionDto.getTransactionDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        Company company = companyRepository.findCompanyById(transactionDto.getCompany().getId());
        Double adebit = Double.valueOf(transactionDto.getAmountDebit());
        Double acredit = Double.valueOf(transactionDto.getAmountCredit());
        Account accountDebit = accountRepository.findOne(transactionDto.getAccountDebit().getId());
        Account accountCredit = accountRepository.findOne(transactionDto.getAccountCredit().getId());
        Currency currency = currencyRepository.findCurrencyById(transactionDto.getCurrency().getId());
        Contractor contractor = contractorRepository.findOne(transactionDto.getContractor().getId());
        TransactionAccount transactionAccount = new TransactionAccount(date, company, adebit,
                accountDebit, acredit, accountCredit, currency);
        transactionAccount.setContractor(contractor);
        TransactionAccount save = transactionRepository.save(transactionAccount);
        return save;
    }
}
