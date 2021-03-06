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
@Table(name = "companies")
public class Company extends BaseIdEntity {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "description")
    private String description;

    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "company_email", unique = true)),
            @AttributeOverride(name = "phone", column = @Column(name = "company_phone"))
    })
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCompany;

    @OneToMany(mappedBy = "company")
    private Set<TransactionAccount> transactionAccountSet = new HashSet<>();

    public Company(String companyName, String description, Contact contact, User userCompany) {
        this.companyName = companyName;
        this.description = description;
        this.contact = contact;
        this.userCompany = userCompany;
    }
}
