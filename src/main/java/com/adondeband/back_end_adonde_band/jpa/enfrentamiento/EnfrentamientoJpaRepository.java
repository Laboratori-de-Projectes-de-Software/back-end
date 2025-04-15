package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnfrentamientoJpaRepository extends JpaRepository<EnfrentamientoEntity, Integer> {
    List<EnfrentamientoEntity> findById(Long id);

    List<EnfrentamientoEntity> findByLiga(LigaEntity ligaEntity);

}
