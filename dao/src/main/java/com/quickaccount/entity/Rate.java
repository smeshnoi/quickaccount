package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency_rates")
public class Rate extends BaseIdEntity {

    @Column(name = "date")
    private LocalDate rateDate;

    @OneToOne
    @JoinColumn(name = "currency_in_id")
    private Currency currencyIn;

    @OneToOne
    @JoinColumn(name = "currency_out_id")
    private Currency currencyOut;

    @Column(name = "rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userRate;
}
