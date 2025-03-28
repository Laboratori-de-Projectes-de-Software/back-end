package org.example.backend.databaseapi.jpa.usuario;

import org.example.backend.databaseapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity,Integer> {

    boolean existsByEmail(String email);

    Optional<UsuarioJpaEntity> findByEmail(String email);
}
