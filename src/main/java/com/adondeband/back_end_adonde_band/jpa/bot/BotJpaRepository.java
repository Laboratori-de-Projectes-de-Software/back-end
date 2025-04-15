package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotJpaRepository extends JpaRepository<BotEntity, String> {
    List<BotEntity> findByNombre(String nombre);

    List<BotEntity> findByUsuario(UsuarioEntity usuario);

    BotEntity getBotEntityByNombre(String nombre);
}
