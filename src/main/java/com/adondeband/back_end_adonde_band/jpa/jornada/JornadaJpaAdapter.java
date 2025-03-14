package com.adondeband.back_end_adonde_band.jpa.jornada;

import com.adondeband.back_end_adonde_band.dominio.jornada.Jornada;
import com.adondeband.back_end_adonde_band.dominio.jornada.JornadaPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JornadaJpaAdapter implements JornadaPort {
    private final JornadaJpaRepository jornadaJpaRepository;

    private final JornadaJpaMapper jornadaJpaMapper;

    public JornadaJpaAdapter(final JornadaJpaRepository jornadaJpaRepository, final JornadaJpaMapper jornadaJpaMapper) {
        this.jornadaJpaRepository = jornadaJpaRepository;
        this.jornadaJpaMapper = jornadaJpaMapper;
    }

    @Override
    public Jornada save(Jornada jornada) {
        return jornadaJpaMapper.toDomain(
                jornadaJpaRepository.save(
                        jornadaJpaMapper.toEntity(jornada)));
    }

    @Override
    public List<Jornada> findById(int s) {
        return jornadaJpaRepository.findById(s)
                .stream()
                .map(jornadaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
