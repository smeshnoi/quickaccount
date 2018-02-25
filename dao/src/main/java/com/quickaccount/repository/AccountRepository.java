package com.quickaccount.repository;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.TypeDC;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    int countAllBy();
    int countAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC);

    List<Account> findAllByAccountNameContainingAndTypeAccountTypeDC(String accountName, TypeDC typeDC, Pageable pageable);
}
