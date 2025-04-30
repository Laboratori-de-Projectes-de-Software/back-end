package org.example.backend.databaseapi.jpa.bot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotJpaRepository extends JpaRepository<BotJpaEntity,Integer> {

    List<BotJpaEntity> findByUsuario_UserId(Integer id);
    boolean existsByNombreOrUrl(String nombre,String url);

}
