package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface BotService {
    Bot crearBot(Bot bot);

    List<Bot> obtenerBotPorNombre(String nombre);

    List<Bot> obtenerTodosLosBots();

    List<Bot> obtenerBotsPorUsuario(UsuarioId userId);
}
