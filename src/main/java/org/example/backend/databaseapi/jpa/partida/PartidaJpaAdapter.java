package org.example.backend.databaseapi.jpa.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.partida.CreatePartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindLigaPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.UpdatePartidaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.jpa.liga.LigaJpaMapper;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class PartidaJpaAdapter implements CreatePartidaPort, FindLigaPartidaPort, UpdatePartidaPort, FindPartidaPort {

    private final LigaJpaMapper ligaJpaMapper;
    private final PartidaJpaRepository partidaJpaRepository;
    private final PartidaJpaMapper partidaJpaMapper;

    @Override
    public Partida createPartida(Partida partida) {
        return partidaJpaMapper.toDomain(
                partidaJpaRepository.save(
                        partidaJpaMapper.toEntity(partida)
                )
        );
    }

    @Override
    public List<Partida> findLigaPartida(Integer idliga) {
        return partidaJpaRepository.findByLiga_LigaId(idliga)
                .stream()
                .map(partidaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Partida updatePartida(Liga liga, Integer duraciontotal, java.util.Date fecha) {
        return null;
    }

    @Override
    public Optional<Partida> findParida(Integer idPartida) {
        return partidaJpaRepository.findById(idPartida)
                .map(partidaJpaMapper::toDomain);
    }
}
