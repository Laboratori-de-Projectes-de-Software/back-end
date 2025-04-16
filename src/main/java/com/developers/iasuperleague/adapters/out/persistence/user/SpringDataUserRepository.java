package com.developers.iasuperleague.adapters.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    // Aquí puedes definir métodos personalizados si los necesitas.
    Optional<UserEntity> findByUsername(String username);
}