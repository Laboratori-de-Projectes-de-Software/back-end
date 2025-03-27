package org.example.backend.databaseapi.jpa.resultado;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.resultado.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.example.backend.databaseapi.jpa.bot.BotJpaAdapter;
import org.example.backend.databaseapi.jpa.bot.BotJpaMapper;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaAdapter;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ResultadoJpaAdapter implements CreateResultadoPort, FindBotResultadoPort, FindPartidaResultadoPort, FindResultadoPort, FindLigaResultadoPort {

    private final ResultadoJpaRepository resultadoJpaRepository;
    private final ResultadoJpaMapper resultadoJpaMapper;
    private final BotJpaAdapter botJpaAdapter;
    private final PartidaJpaAdapter partidaJpaAdapter;
    private final BotJpaMapper botJpaMapper;
    private final PartidaJpaMapper partidaJpaMapper;

    @Override
    @Transactional
    public Resultado createResultado(Resultado resultado) {
        ResultadoJpaEntity resultjpa=ResultadoJpaEntity.builder()
                .bot(
                        botJpaAdapter.findJpaBot(resultado.getResultadoId().botvalue())
                                .orElseThrow()
                )
                .partida(
                        partidaJpaAdapter.findJpaPartida(resultado.getResultadoId().partidavalue())
                                .orElseThrow()
                )
                .build();
        return resultadoJpaMapper.toDomain(
                resultadoJpaRepository.save(resultjpa)
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
    @Transactional
    public Optional<Resultado> findResultado(Integer botId, Integer partidaId) {

        ResultadoIdJpa resultadoId=new ResultadoIdJpa(
                botJpaAdapter.findJpaBot(botId).orElseThrow(),
                partidaJpaAdapter.findJpaPartida(partidaId).orElseThrow()
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
