package com.example.demo.application.port.out;

import com.example.demo.adapters.out.persistence.user.UserEntity;
import com.example.demo.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User register(User user);
    User findByUsername(String username);
}