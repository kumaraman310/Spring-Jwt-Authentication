package com.jwt.authentication.repository;

import com.jwt.authentication.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDetailRepository extends JpaRepository<RoleEntity,String> {
}
