package com.debateia.adapter.out.user;

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
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.entityToDomain(userJpaRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userJpaRepository.findById(id).map(userMapper::entityToDomain);
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return userJpaRepository.findByMail(mail).map(userMapper::entityToDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userMapper::entityToDomain);
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
