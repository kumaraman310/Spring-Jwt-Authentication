package com.jwt.authentication.repository;

import com.jwt.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserName(String userName);

}
