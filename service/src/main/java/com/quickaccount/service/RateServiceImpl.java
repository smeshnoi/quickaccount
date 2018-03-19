package com.quickaccount.service;

import com.quickaccount.dto.RateDto;
import com.quickaccount.entity.Currency;
import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import com.quickaccount.repository.CurrencyRepository;
import com.quickaccount.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class RateServiceImpl implements RateService {
    private RateRepository rateRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository, CurrencyRepository currencyRepository) {
        this.rateRepository = rateRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Rate save(RateDto rate, User user) {
        LocalDate date = LocalDate.parse(rate.getRateDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        Double aDouble = Double.valueOf(rate.getRate());
        Currency currIn = currencyRepository.findCurrencyById(rate.getCurrencyIn());
        Currency currOut = currencyRepository.findCurrencyById(rate.getCurrencyOut());
        Rate rate1 = new Rate(date, currIn, currOut, aDouble, user);
        return rateRepository.save(rate1);
    }

    @Override
    public List<Rate> findAllByUser(User user) {
        return rateRepository.getAllByUserRate(user);
    }

    @Override
    public List<Rate> findAllByDate(User user, LocalDate date) {
        return rateRepository.findAllByUserRateAndRateDate(user, date);
    }
}
