package org.example.backend.databaseapi.jpa.partida;

import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaJpaRepository extends JpaRepository<PartidaJpaEntity,Integer> {

    List<PartidaJpaEntity> findByLiga_LigaId(Integer ligaid);

}
