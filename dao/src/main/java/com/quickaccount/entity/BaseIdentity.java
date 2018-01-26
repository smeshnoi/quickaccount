package com.quickaccount.entity;

import javax.persistence.Id;

public abstract class BaseIdentity {
    @Id
    private Long id;
}
