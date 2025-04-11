package com.debateia.application.ports.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debateia.adapter.out.persistence.entities.UserEntity;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByMail(String mail);
  Optional<UserEntity> findByUsername(String username);
  boolean existsByMail(String email);
  boolean existsByUsername(String username);
}
