package com.debateia.application.ports.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debateia.adapter.out.persistence.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByMail(String mail);

  boolean existsByMail(String email);
}
