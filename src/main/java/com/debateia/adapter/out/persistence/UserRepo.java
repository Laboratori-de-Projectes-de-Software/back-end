package com.debateia.adapter.out.persistence;

import com.debateia.adapter.mapper.UserMapper;
import com.debateia.application.ports.out.persistence.UserRepository;

import java.util.Optional;

import com.debateia.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepo implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return UserMapper.entityToDomain(userJpaRepository.save(UserMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userJpaRepository.findById(id).map(UserMapper::entityToDomain);
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return userJpaRepository.findByMail(mail).map(UserMapper::entityToDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserMapper::entityToDomain);
    }

    @Override
    public boolean existsByMail(String email) {
        return userJpaRepository.existsByMail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }
}
