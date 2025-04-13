package com.example.demo.adapters.out.persistence.user;

import com.example.demo.adapters.out.persistence.League.LeagueEntity;
import com.example.demo.application.port.out.UserRepository;
import com.example.demo.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa el puerto de salida usando Spring Data JPA.
 */
@Component
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository jpaRepository;

    public UserPersistenceAdapter(SpringDataUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User register(User user) {
        // Mapeo de dominio a entidad JPA
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setMail(user.getMail());
        entity.setPassword(user.getPassword());
        entity.setCreatedAt(user.getCreatedAt());

        UserEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity entity = jpaRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return toDomain(entity);
    }

    private User toDomain(UserEntity entity) {
        User user = new User(entity.getUsername(), entity.getMail(), entity.getPassword());
        user.setId(entity.getId());
        user.setCreatedAt(entity.getCreatedAt());
        return user;
    }
}
