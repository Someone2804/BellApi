package com.bell.BellApi.model.role;


import com.bell.BellApi.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    ERole name;

    @OneToMany(mappedBy = "securityUser.role")
    Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }
}
