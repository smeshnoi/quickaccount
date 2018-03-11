package com.quickaccount.repository;

import com.quickaccount.entity.Company;
import com.quickaccount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByUserCompany(User user);
}
