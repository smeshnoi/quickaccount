package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
public class Currency extends BaseIdEntity {

    @Column(name = "currency", unique = true, nullable = false)
    private String currency;

    @OneToMany(mappedBy = "currency")
    private Set<User> userSet = new HashSet<>();

    @OneToOne(mappedBy = "currencyIn")
    private Rate rateIn;

    @OneToOne(mappedBy = "currencyOut")
    private Rate rateOut;

    @OneToMany(mappedBy = "currency")
    private Set<TransactionAccount> transactionAccountSet = new HashSet<>();

    public Currency(String currency) {
        this.currency = currency;
    }
}
