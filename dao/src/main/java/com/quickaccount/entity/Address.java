package com.quickaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Address extends BaseIdEntity {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "office")
    private int office;

    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private ContractorLegalEntity contractorLegalEntity;

    public Address(Long id, String city, String street, String building, int office) {
        super(id);
        this.city = city;
        this.street = street;
        this.building = building;
        this.office = office;
    }
}
