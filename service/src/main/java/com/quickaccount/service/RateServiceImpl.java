package com.quickaccount.service;

import com.quickaccount.entity.Rate;
import com.quickaccount.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RateServiceImpl implements RateService {
    private RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate save(Rate rate) {
        return null;
    }
}
