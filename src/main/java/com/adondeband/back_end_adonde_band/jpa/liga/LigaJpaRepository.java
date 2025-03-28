package com.adondeband.back_end_adonde_band.jpa.liga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LigaJpaRepository extends JpaRepository<LigaEntity, Integer> {
    List<LigaEntity> findByNombre(String nombre);
}
