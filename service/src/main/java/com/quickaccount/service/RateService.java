package com.quickaccount.service;

import com.quickaccount.dto.RateDto;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;

import java.util.List;

public interface RateService {
    Rate save(RateDto rate, User user);
    List<Rate> findAllByUser(User user);
}
