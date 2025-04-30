package org.example.backend.databaseapi.jpa.mensaje;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeJpaRepository extends JpaRepository<MensajeJpaEntity,Integer> {

    List<MensajeJpaEntity> findByPartida_PartidaId(Integer partidaId);

    List<MensajeJpaEntity> findByBot_IdBot(Integer botId);

}
