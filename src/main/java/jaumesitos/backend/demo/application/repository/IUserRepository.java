package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.User;
import java.util.Optional;

public interface IUserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}