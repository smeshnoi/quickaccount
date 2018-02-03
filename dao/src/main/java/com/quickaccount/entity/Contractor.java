package com.quickaccount.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@ToString(callSuper = true)
@Table(name = "contractors")
public class Contractor extends BaseIdEntity {

    @Column(name = "contractor_name", nullable = false, unique = true)
    private String contractorName;

}
