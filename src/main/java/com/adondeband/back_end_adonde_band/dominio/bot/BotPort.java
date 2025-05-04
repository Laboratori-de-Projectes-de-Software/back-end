package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface BotPort {
    Bot save(Bot bot);

    Bot findByNombre(String s);

    Bot findById(Long id);

    List<Bot> findAll();

    List<Bot> findBotsUsuario(UsuarioId userId);

    Bot actualizarUrlImagen(BotId botId, String url);

    Bot actualizarDescripcion(BotId botId, String descripcion);

    Bot actualizarEndpoint(BotId botId, String endpoint);
}
