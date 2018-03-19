package com.quickaccount.repository;

import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    List<Contractor> findAllByUserContractorOrderByContractorName(User user);
    List<Contractor> findAllByUserContractor(User user);
}
