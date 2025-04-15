package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BotImpl implements BotService {

    private final BotPort botPort;

    public BotImpl(BotPort botPort) {
        this.botPort = botPort;
    }

    @Override
    @Transactional
    public Bot crearBot(Bot bot) {
        if (botPort.findByNombre(bot.getNombre()) == null) {
            return botPort.save(bot);
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }

    @Override
    public Bot obtenerBotPorNombre(String nombre) {
        return botPort.findByNombre(nombre);
    }

    @Override
    public List<Bot> obtenerTodosLosBots() {
        return botPort.findAll();
    }

    @Override
    public List<Bot> obtenerBotsPorIdUsuario(UsuarioId userId) {
        return botPort.findBotsUsuario(userId);
    }

    @Override
    public Bot obtenerBotPorId(Long id) {
        return botPort.findById(id);
    }

    @Override
    public List<String> getBotNames(List<BotId> botIds) {
        List <String> botNames = new ArrayList<>(botIds.size());

        for (BotId botId : botIds) {
            // obtener bot y añadir el nombre a la lista
            Bot bot = obtenerBotPorId(botId.value());
            botNames.add(bot.getNombre());
        }

        return botNames;
    }
}
