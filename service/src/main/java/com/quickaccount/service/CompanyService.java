package com.quickaccount.service;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.User;

import java.util.List;

public interface CompanyService {
    List<Company> findAllByUserCompany(User user);
    Company save(Company company);
    Company findCompanyById(Long id);
    Company update(Company company, Long id);
}
