package com.adondeband.back_end_adonde_band.jpa.jornada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JornadaJpaRepository extends JpaRepository<JornadaEntity, Long> {
    List<JornadaEntity> findById(long Id);
}
