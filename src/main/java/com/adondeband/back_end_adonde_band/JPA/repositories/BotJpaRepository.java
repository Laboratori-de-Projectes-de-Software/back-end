package com.adondeband.back_end_adonde_band.JPA.repositories;

import com.adondeband.back_end_adonde_band.JPA.entities.BotEntity;
import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotJpaRepository extends JpaRepository<BotEntity, Integer> {

    List<BotEntity> findByNombre(String nombre);
}
