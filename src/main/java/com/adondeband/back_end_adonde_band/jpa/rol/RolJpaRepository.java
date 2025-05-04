package com.adondeband.back_end_adonde_band.jpa.rol;

import com.adondeband.back_end_adonde_band.jpa.rol.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolJpaRepository extends JpaRepository<RolEntity, Integer> {
    List<RolEntity> findByNombre(String nombre);
}
