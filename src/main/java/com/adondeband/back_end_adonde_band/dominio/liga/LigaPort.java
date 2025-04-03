package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface LigaPort {
    Liga save(Liga liga);

    List<Liga> findById(LigaId id);

    List<Liga> findAll();

    List<Liga> findLigasUsuario(UsuarioId userId);
}
