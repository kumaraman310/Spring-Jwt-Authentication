package com.jwt.authentication.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private String userName;

    private String userFirstName;

    private String userLastName;

    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")
            })
    private Set<RoleEntity> role;
}
