package com.quickaccount.service;

import com.quickaccount.dto.TransactionDto;
import com.quickaccount.entity.Company;
import com.quickaccount.entity.TransactionAccount;
import com.quickaccount.entity.User;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionAccount> findAllByCompany(Company company);

    TransactionAccount addTransacton(TransactionDto transactionDto, User userbyLogin);

    List<TransactionAccount> findAllByUser(List<Company> companyList);
    List<TransactionAccount> findAllByUser(List<Company> companyList, Pageable pageable);

    List<TransactionAccount> findAllByTransactionDateBetweenAndCompanyInOrderByTransactionDate(LocalDate dateStart, LocalDate dateEnd, List<Company> companyList);

    int countAllByCompanyInOrderByTransactionDate(List<Company> allByUserCompany);

    TransactionAccount findTransactionById(Long id);
}
