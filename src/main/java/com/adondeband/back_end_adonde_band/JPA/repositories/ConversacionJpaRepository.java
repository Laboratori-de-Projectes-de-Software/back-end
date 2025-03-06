package com.adondeband.back_end_adonde_band.JPA.repositories;

import com.adondeband.back_end_adonde_band.JPA.entities.ConversacionEntity;
import com.adondeband.back_end_adonde_band.JPA.entities.EnfrentamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversacionJpaRepository extends JpaRepository<ConversacionEntity, Integer> {
}
