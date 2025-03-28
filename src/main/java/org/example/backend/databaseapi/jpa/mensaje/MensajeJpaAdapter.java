package org.example.backend.databaseapi.jpa.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.mensaje.CreateMensajePort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajeBotPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePartidaPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePort;
import org.example.backend.databaseapi.domain.Bot;
import org.example.backend.databaseapi.domain.Mensaje;
import org.example.backend.databaseapi.domain.Partida;
import org.example.backend.databaseapi.jpa.bot.BotJpaMapper;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MensajeJpaAdapter implements FindMensajePort, FindMensajePartidaPort, FindMensajeBotPort, CreateMensajePort {

    private final MensajeJpaRepository mensajeJpaRepository;
    private final MensajeJpaMapper mensajeJpaMapper;
    private final BotJpaMapper botJpaMapper;
    private final PartidaJpaMapper partidaJpaMapper;

    @Override
    public List<Mensaje> findMensajeBot(Integer botId) {
        return mensajeJpaRepository.findByBot_IdBot(botId)
                .stream()
                .map(mensajeJpaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Mensaje> findMensajePartida(Integer idPartida) {
        return mensajeJpaRepository.findByPartida_PartidaId(idPartida)
                .stream()
                .map(mensajeJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Mensaje> findMensaje(Integer idMensaje) {
        return mensajeJpaRepository.findById(idMensaje).map(mensajeJpaMapper::toDomain);
    }

    @Override
    public Mensaje createMensaje(Mensaje mensaje){

        return mensajeJpaMapper.toDomain(
                mensajeJpaRepository.save(
                        mensajeJpaMapper.toEntity(mensaje)
                )
        );
    }
}
