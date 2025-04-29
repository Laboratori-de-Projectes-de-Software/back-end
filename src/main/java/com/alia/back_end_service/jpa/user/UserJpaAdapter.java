package com.alia.back_end_service.jpa.user;


import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJpaAdapter implements UserPortDB {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    public UserJpaAdapter(final UserJpaRepository userJpaRepository, final UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> userEntity = userJpaRepository.findByUsername(username);
        return userEntity.map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = userJpaRepository.findByMail(email);
        return userEntity.map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity savedEntity = userJpaRepository.save(userMapper.toEntity(user));
        // No asignamos bots en el alta de un Usuario
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(String username) {
        userJpaRepository.deleteByUsername(username);
    }
}
