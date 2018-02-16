package com.quickaccount.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency_rates")
@ToString(callSuper = true)
public class Rate extends BaseIdEntity {

    @Column(name = "date")
    private LocalDate rateDate;

    @ManyToOne
    @JoinColumn(name = "currency_in_id")
    private Currency currencyIn;

    @ManyToOne
    @JoinColumn(name = "currency_out_id")
    private Currency currencyOut;

    @Column(name = "rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userRate;
}
