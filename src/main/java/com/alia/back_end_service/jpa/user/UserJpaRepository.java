package com.alia.back_end_service.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByMail(String email);
    void deleteByUsername(String username);

}
