package com.adondeband.back_end_adonde_band.jpa.participacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacionJpaRepository extends JpaRepository<ParticipacionEntity, Long> {}
