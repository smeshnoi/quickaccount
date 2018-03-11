package com.quickaccount.service;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.User;
import com.quickaccount.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllByUserCompany(User user) {
        return companyRepository.findAllByUserCompany(user);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
