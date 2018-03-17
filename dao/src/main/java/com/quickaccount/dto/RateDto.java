package com.quickaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    @NotEmpty(message = "date not null")
    private String rateDate;

    @NotEmpty(message = "not null")
    private double rate;

    @NotEmpty
    private Long currencyIn;

    @NotEmpty
    private Long currencyOut;
}
