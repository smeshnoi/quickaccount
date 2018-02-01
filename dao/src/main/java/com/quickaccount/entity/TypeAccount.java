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
@Table(name = "account_types")
public class TypeAccount extends BaseIdentity {

    @Column(name = "name", nullable = false)
    private String typeAccountName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_dc", nullable = false)
    private TypeDC typeDC;
}
