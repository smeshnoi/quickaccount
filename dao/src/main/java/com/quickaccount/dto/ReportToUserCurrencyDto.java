package com.quickaccount.dto;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.Company;
import com.quickaccount.entity.Contractor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportToUserCurrencyDto {

    private Long id;
    private LocalDate dateTransaction;
    private Double amountDebit;
    private Account accountDebit;
    private Double amountCredit;
    private Account accountCredit;
    private Company company;
    private Contractor contractor;

    public ReportToUserCurrencyDto(LocalDate transactionDate, Double amountDebit, Account accountDebit, Double amountCredit, Account accountCredit, Company company, Contractor contractor) {
    }
}
