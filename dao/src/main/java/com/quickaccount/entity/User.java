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
@Table(name = "users")
public class User extends BaseIdEntity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "password", nullable = false)
    private String password;

    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "user_email", unique = true, nullable = false)),
            @AttributeOverride(name = "phone", column = @Column(name = "user_phone"))
    })
    private Contact contact;

    @OneToMany(mappedBy = "userCompany")
    private Set<Company> companySet = new HashSet<>();

    @OneToMany(mappedBy = "userRate")
    private Set<Rate> rateHashSet = new HashSet<>();

    @OneToMany(mappedBy = "userAccount")
    private Set<Account> accountHashSet = new HashSet<>();

    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userContractor")
    private Set<Contractor> contractorSet = new HashSet<>();

//    @OneToMany(mappedBy = "userTransaction")
//    private Set<Transaction> transactionSetSet = new HashSet<>();

    public User(String login, String firstName, String lastName, Currency currency, String password, Contact contact) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currency = currency;
        this.password = password;
        this.contact = contact;
    }
}
