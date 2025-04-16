package com.debateia.application.ports.out.persistence;

import com.debateia.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.debateia.adapter.out.user.UserEntity;

import java.util.Optional;
import java.util.List;


public interface UserRepository {
  User save(User user);
  Optional<User> findById(Integer id);
  Optional<User> findByMail(String mail);
  Optional<User> findByUsername(String username);
  boolean existsByMail(String email);
  boolean existsByUsername(String username);
}
