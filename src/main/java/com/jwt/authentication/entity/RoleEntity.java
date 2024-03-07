package com.jwt.authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="role")
public class RoleEntity {

    @Id
    private String roleName;

    private String roleDescription;
}
