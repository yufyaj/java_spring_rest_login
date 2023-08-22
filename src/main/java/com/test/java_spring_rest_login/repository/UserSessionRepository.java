package com.test.java_spring_rest_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.java_spring_rest_login.model.UserSessionEntity;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionEntity, String>{
    Optional<UserSessionEntity> findBySessionId(String SessionId);
    Optional<UserSessionEntity> findByUserId(String UserId);
}