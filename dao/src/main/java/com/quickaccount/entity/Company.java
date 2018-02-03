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
@Table(name = "companies")
public class Company extends BaseIdEntity {

    @Column(name = "company_name")
    private String company_name;

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

    public Company(String company_name, String description, Contact contact) {
        this.company_name = company_name;
        this.description = description;
        this.contact = contact;
    }
}
