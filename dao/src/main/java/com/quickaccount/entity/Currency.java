package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "currencyIn")
    private List<Rate> rateIn;

    @OneToMany(mappedBy = "currencyOut")
    private List<Rate> rateOut;

    @OneToMany(mappedBy = "currency")
    private Set<TransactionAccount> transactionAccountSet = new HashSet<>();

    public Currency(String currency) {
        this.currency = currency;
    }
}
