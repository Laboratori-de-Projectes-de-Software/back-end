package com.adondeband.back_end_adonde_band.jpa.imagen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenJpaRepository extends JpaRepository<ImagenEntity, Long> {
    List<ImagenEntity> findByRuta(String ruta);
}
