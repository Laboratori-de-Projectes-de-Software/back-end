package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.LigaMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.LigaJpaRepository;
import com.adondeband.back_end_adonde_band.api.dominio.modelos.Liga;
import com.adondeband.back_end_adonde_band.api.dominio.puertos.LigaPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigaJpaAdapter implements LigaPort {

    private final LigaJpaRepository ligaJpaRepository;

    private final LigaMapper ligaMapper;

    public LigaJpaAdapter(final LigaJpaRepository ligaJpaRepository, final LigaMapper ligaMapper) {
        this.ligaJpaRepository = ligaJpaRepository;
        this.ligaMapper = ligaMapper;
    }

    @Override
    public Liga save(Liga liga) {
        return ligaMapper.toDomain(
                ligaJpaRepository.save(
                        ligaMapper.toEntity(liga)));
    }

    @Override
    public List<Liga> findByNombre(String s) {
        return ligaJpaRepository.findByNombre(s)
                .stream()
                .map(ligaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
