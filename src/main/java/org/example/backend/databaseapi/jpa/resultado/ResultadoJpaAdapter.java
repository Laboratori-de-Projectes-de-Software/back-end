package org.example.backend.databaseapi.jpa.resultado;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.resultado.*;
import org.example.backend.databaseapi.domain.Bot;
import org.example.backend.databaseapi.domain.Partida;
import org.example.backend.databaseapi.domain.Resultado;
import org.example.backend.databaseapi.jpa.bot.BotJpaMapper;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ResultadoJpaAdapter implements CreateResultadoPort, FindBotResultadoPort, FindPartidaResultadoPort, FindResultadoPort, FindLigaResultadoPort {

    private final ResultadoJpaRepository resultadoJpaRepository;
    private final ResultadoJpaMapper resultadoJpaMapper;
    private final BotJpaMapper botJpaMapper;
    private final PartidaJpaMapper partidaJpaMapper;

    @Override
    @Transactional
    public Resultado createResultado(Resultado resultado) {
        return resultadoJpaMapper.toDomain(
                resultadoJpaRepository.save(
                        resultadoJpaMapper.toEntity(resultado)
                )
        );
    }

    @Override
    public List<Resultado> findBotResultados(Integer idBot) {
        return resultadoJpaRepository.findByBot_IdBot(idBot)
                .stream()
                .map(resultadoJpaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Resultado> findPartidaResultado(int idPartida) {
        return resultadoJpaRepository.findByPartida_PartidaId(idPartida)
                .stream()
                .map(resultadoJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Resultado> findResultado(Bot bot, Partida partida) {
        ResultadoId resultadoId=new ResultadoId(
                botJpaMapper.toEntity(bot),
                partidaJpaMapper.toEntity(partida)
        );
        return resultadoJpaRepository.findById(resultadoId)
                .map(resultadoJpaMapper::toDomain);
    }

    @Override
    public List<Resultado> findResultadoLiga(Integer ligaId) {
        return resultadoJpaRepository.findByPartida_Liga_LigaId(ligaId)
                .stream()
                .map(resultadoJpaMapper::toDomain)
                .toList();
    }
}
