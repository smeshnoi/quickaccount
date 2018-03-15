package com.quickaccount.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseIdEntity {

    @Column(name = "name_role")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
