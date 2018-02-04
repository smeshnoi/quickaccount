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
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_dc", nullable = false)
    private TypeDC typeDC;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    public TransactionAccount(LocalDate date, Company company, Account account, TypeDC typeDC, Currency currency, Double amount, Contractor contractor) {
        this.date = date;
        this.company = company;
        this.account = account;
        this.typeDC = typeDC;
        this.currency = currency;
        this.amount = amount;
        this.contractor = contractor;
    }
}
