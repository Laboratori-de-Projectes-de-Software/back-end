package org.example.backend.databaseapi.jpa.mensaje;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.mensaje.CreateMensajePort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajeBotPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePartidaPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.example.backend.databaseapi.jpa.bot.BotJpaAdapter;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MensajeJpaAdapter implements FindMensajePort, FindMensajePartidaPort, FindMensajeBotPort, CreateMensajePort {

    private final MensajeJpaRepository mensajeJpaRepository;
    private final MensajeJpaMapper mensajeJpaMapper;
    private final PartidaJpaAdapter partidaJpaAdapter;
    private final BotJpaAdapter botJpaAdapter;

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
    @Transactional
    public Mensaje createMensaje(Mensaje mensaje){
        MensajeJpaEntity entity=MensajeJpaEntity.builder()
                .partida(partidaJpaAdapter.getJpaPartida(mensaje.getPartida().value())
                        .orElseThrow())
                .bot(botJpaAdapter.getJpaBot(mensaje.getBot().value())
                        .orElseThrow())
                .hora(mensaje.getHora())
                .texto(mensaje.getTexto())
                .build();
        return mensajeJpaMapper.toDomain(
                mensajeJpaRepository.save(entity)
        );
    }
}
