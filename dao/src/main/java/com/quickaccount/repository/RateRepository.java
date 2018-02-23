package com.quickaccount.repository;

import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Long> {

    List<Rate> getAllByUserRate (User user);
}
