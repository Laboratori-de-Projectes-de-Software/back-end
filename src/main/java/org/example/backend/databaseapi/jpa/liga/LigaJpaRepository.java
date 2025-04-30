package org.example.backend.databaseapi.jpa.liga;

import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaJpaRepository extends JpaRepository<LigaJpaEntity,Integer> {
    List<LigaJpaEntity> findByUsuario_UserId(Integer userid);
    boolean existsByNombre(String nombre);
}
