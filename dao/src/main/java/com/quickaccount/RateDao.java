package com.quickaccount;

import com.quickaccount.entity.Rate;
import com.quickaccount.entity.User;

import java.util.List;

public interface RateDao extends BaseDao<Rate> {
    List<Rate> getRatesByUser(User user);
}
