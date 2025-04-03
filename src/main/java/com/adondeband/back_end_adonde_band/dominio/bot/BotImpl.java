package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BotImpl implements BotService {

    private final BotPort botPort;

    public BotImpl(BotPort botPort) {
        this.botPort = botPort;
    }

    @Override
    public Bot crearBot(Bot bot) {
        if (botPort.findByNombre(bot.getNombre().value()).isEmpty()) {
            return botPort.save(bot);
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }

    @Override
    public List<Bot> obtenerBotPorNombre(String nombre) {
        return botPort.findByNombre(nombre);
    }

    @Override
    public List<Bot> obtenerTodosLosBots() {
        return botPort.findAll();
    }

    @Override
    public List<Bot> obtenerBotsPorUsuario(UsuarioId userId) {
        return botPort.findBotsUsuario(userId);
    }
}
