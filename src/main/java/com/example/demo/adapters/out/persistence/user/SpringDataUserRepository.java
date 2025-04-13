package com.example.demo.adapters.out.persistence.user;

import com.example.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    // Aquí puedes definir métodos personalizados si los necesitas.
    Optional<UserEntity> findByUsername(String username);
}