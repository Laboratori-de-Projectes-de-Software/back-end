package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LigaJpaRepository extends JpaRepository<LigaEntity, Integer> {
    List<LigaEntity> findById(Long id);

    List<LigaEntity> findByUsuario(UsuarioEntity usuario);
}
