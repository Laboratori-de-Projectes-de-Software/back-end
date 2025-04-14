package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LigaJpaRepository extends JpaRepository<LigaEntity, Long> {
    List<LigaEntity> findById(long id);

    List<ParticipacionEntity> findParticipacionesById(long ligaId);
}