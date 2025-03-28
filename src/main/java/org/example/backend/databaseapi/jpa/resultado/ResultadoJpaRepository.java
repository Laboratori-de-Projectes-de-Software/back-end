package org.example.backend.databaseapi.jpa.resultado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoJpaRepository extends JpaRepository<ResultadoJpaEntity,ResultadoId> {

    List<ResultadoJpaEntity> findByBot_IdBot(int idBot);
    List<ResultadoJpaEntity> findByPartida_Liga_LigaId(int idLiga);
    List<ResultadoJpaEntity> findByPartida_PartidaId(int partidaId);

}
