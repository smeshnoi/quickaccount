package com.quickaccount.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
@ToString(callSuper = true)
public class TransactionAccount extends BaseIdEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "account_debit_id", nullable = false)
    private Account accountDebit;

    @ManyToOne
    @JoinColumn(name = "account_credit_id", nullable = false)
    private Account accountCredit;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_dc", nullable = false)
    private TypeDC typeDC;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "amount_debit", nullable = false)
    private Double amountDebit;

    @Column(name = "amount_credit", nullable = false)
    private Double amountCredit;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userTransaction;

    public TransactionAccount(LocalDate date, Company company, Double amountDebit, Account accountDebit,
                              Double amountCredit, Account accountCredit, Currency currency
                              ) {
        this.date = date;
        this.company = company;
        this.amountDebit = amountDebit;
        this.accountDebit = accountDebit;
        this.amountCredit = amountCredit;
        this.accountCredit = accountCredit;
        this.currency = currency;
        //this.contractor = contractor;
    }
}
