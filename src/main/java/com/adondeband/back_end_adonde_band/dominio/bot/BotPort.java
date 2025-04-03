package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface BotPort {
    Bot save(Bot bot);

    List<Bot> findByNombre(String s);

    List<Bot> findAll();

    List<Bot> findBotsUsuario(UsuarioId userId);
}
