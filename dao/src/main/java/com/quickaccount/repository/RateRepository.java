package com.quickaccount.repository;

import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> getAllByUserRate(User user);
    List<Rate> findAllByUserRateAndRateDate(User user, LocalDate date);
}
