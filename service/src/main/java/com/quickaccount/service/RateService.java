package com.quickaccount.service;

import com.quickaccount.dto.RateDto;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface RateService {
    Rate save(RateDto rate, User user);
    List<Rate> findAllByUser(User user);
    List<Rate> findAllByDate(User user, LocalDate date);
}
