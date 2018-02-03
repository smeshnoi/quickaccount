package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseIdEntity {

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "type_account_id", nullable = false)
    private TypeAccount typeAccount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userAccount;
}
