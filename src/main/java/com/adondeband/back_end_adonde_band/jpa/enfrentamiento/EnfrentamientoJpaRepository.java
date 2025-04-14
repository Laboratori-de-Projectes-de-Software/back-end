package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfrentamientoJpaRepository extends JpaRepository<EnfrentamientoEntity, Integer> {
    List<EnfrentamientoEntity> findById(Long id);
}
