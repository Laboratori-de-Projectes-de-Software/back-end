package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfrentamientoJpaRepository extends JpaRepository<EnfrentamientoEntity, Long> {}
