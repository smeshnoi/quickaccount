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
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

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

    public User(String login, String first_name, String last_name, Currency currency, String password, Role role, Contact contact) {
        this.login = login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.currency = currency;
        this.password = password;
        this.role = role;
        this.contact = contact;
    }
}
