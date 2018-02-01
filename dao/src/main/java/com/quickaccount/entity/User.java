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
public class User extends BaseIdentity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "currency_id", nullable = false)
    @OneToOne(mappedBy = "user")
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

    @OneToMany(mappedBy = "user")
    private Set<Company> companySet = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Rate> rateHashSet = new HashSet<>();
}
