package com.quickaccount.dto;

import com.quickaccount.entity.Account;
import com.quickaccount.entity.Company;
import com.quickaccount.entity.Contractor;
import com.quickaccount.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotEmpty(message = "date not null")
    private String transactionDate;

    @NotEmpty(message = "not null")
    private double amountDebit;

    @NotEmpty(message = "not null")
    private double amountCredit;

    @NotEmpty(message = "not null")
    private Account accountDebit;

    @NotEmpty(message = "not null")
    private Account accountCredit;

    @NotEmpty(message = "not null")
    private Company company;

    @NotEmpty(message = "not null")
    private Currency currency;

    @NotEmpty(message = "not null")
    private Contractor contractor;

    private String description;
}
