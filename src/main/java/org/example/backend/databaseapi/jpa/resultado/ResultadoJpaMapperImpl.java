package org.example.backend.databaseapi.jpa.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.domain.Resultado;
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
                botJpaMapper.toDomain(entity.getBot()),
                partidaJpaMapper.toDomain(entity.getPartida()),
                entity.getPuntuacion()
        );
    }

    @Override
    public ResultadoJpaEntity toEntity(Resultado resultado) {
        return new ResultadoJpaEntity(
                botJpaMapper.toEntity(resultado.getBot()),
                partidaJpaMapper.toEntity(resultado.getPartida()),
                resultado.getPuntuacion()
        );
    }
}
