package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
public class Currency extends BaseIdentity {

    @Column(name = "currency", unique = true, nullable = false)
    private String currency;

    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private User user;

    @OneToOne(mappedBy = "currencyIn")
    private Rate rateIn;

    @OneToOne(mappedBy = "currencyOut")
    private Rate rateOut;
}
