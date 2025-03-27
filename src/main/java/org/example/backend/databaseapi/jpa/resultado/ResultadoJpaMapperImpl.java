package org.example.backend.databaseapi.jpa.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.example.backend.databaseapi.domain.resultado.ResultadoId;
import org.example.backend.databaseapi.jpa.bot.BotJpaMapper;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResultadoJpaMapperImpl implements ResultadoJpaMapper{

    private final PartidaJpaMapper partidaJpaMapper;
    private final BotJpaMapper botJpaMapper;

    @Override
    public Resultado toDomain(ResultadoJpaEntity entity) {
        return new Resultado(
                new ResultadoId(
                        entity.getBot().getIdBot(),
                        entity.getPartida().getPartidaId()),
                entity.getPuntuacion()
        );
    }


}
