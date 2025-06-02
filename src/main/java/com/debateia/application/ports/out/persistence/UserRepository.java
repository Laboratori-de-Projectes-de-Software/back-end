package com.debateia.application.ports.out.persistence;

import com.debateia.domain.User;

import java.util.Optional;


public interface UserRepository {
  User save(User user);
  Optional<User> findById(Integer id);
  Optional<User> findByMail(String mail);
  Optional<User> findByUsername(String username);
  boolean existsByMail(String email);
  boolean existsByUsername(String username);
}
