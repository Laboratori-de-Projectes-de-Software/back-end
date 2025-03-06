package com.adondeband.back_end_adonde_band.JPA.repositories;

import com.adondeband.back_end_adonde_band.JPA.entities.EnfrentamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnfrentamientoJpaRepository extends JpaRepository<EnfrentamientoEntity, Long> {}
