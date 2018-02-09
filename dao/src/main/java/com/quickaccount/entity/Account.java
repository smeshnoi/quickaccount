package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "account")
    private Set<TransactionAccount> transactionAccountSet = new HashSet<>();

    public Account(String accountName, TypeAccount typeAccount, User userAccount) {
        this.accountName = accountName;
        this.typeAccount = typeAccount;
        this.userAccount = userAccount;
    }
}
