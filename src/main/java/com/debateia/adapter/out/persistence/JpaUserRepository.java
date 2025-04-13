package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.application.mapper.UserMapper;
import com.debateia.application.ports.out.persistence.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.debateia.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {
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
