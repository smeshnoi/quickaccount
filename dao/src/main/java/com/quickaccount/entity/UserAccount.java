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
@Table(name = "user_accounts")
public class UserAccount extends BaseIdEntity {

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "type_account_id", nullable = false)
    private TypeAccount typeAccount;

}
