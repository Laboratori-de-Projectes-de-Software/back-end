package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacionJpaRepository extends JpaRepository<ParticipacionEntity, Long> {
    List<ParticipacionEntity> findByLiga(LigaEntity liga, Sort sort);
}
