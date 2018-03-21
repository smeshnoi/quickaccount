package com.quickaccount.dto;

import com.quickaccount.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAccountBalanceDto {
    private Account account;
    private Double amount;
}
