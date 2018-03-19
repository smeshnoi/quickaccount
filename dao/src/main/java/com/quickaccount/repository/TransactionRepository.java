package com.quickaccount.repository;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.TransactionAccount;
import com.quickaccount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionAccount, Long> {
    List<TransactionAccount> findAllByCompanyUserCompany(Company company);
    List<TransactionAccount> findAllByCompanyUserCompany(User user);
}
