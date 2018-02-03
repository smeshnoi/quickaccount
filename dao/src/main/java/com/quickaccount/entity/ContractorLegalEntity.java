package com.quickaccount.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contractor_legal_entity")
@PrimaryKeyJoinColumn(name = "contractor_id")
public class ContractorLegalEntity extends Contractor {

    @Column(name = "account")
    private String account;

    @Column(name = "unn")
    private String unn;

    @OneToOne(mappedBy = "contractorLegalEntity")
    private Address address;
}
