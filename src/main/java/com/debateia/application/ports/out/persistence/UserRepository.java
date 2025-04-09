package com.debateia.application.ports.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debateia.adapter.out.persistence.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
