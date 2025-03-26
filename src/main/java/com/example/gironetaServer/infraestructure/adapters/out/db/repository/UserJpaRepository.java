package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsernameAndEmail(String username, String email);

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}