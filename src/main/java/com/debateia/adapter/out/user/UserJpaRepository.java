package com.debateia.adapter.out.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByMail(String mail);
    Optional<UserEntity> findByUsername(String username);
    boolean existsByMail(String email);
    boolean existsByUsername(String username);
}
