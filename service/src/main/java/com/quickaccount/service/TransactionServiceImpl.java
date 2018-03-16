package com.quickaccount.service;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.TransactionAccount;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<TransactionAccount> findAllByCompany(Company company) {
        return null;
    }
}
