package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDatabaseRepository implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userJpaRepository.findById(id).orElse(null);
        return userEntity != null ? toDomain(userEntity) : null;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = toEntity(user);
        userEntity = userJpaRepository.save(userEntity);
        return toDomain(userEntity);
    }

    @Override
    public List<User> getUsers() {
        return userJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity userEntity = userJpaRepository.findByUsername(username);
        return userEntity != null ? toDomain(userEntity) : null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::toDomain)
                .orElse(null);
    }

    private User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt());
    }

    private UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setCreatedAt(user.getCreatedAt());
        userEntity.setUpdatedAt(user.getUpdatedAt());
        return userEntity;
    }
}