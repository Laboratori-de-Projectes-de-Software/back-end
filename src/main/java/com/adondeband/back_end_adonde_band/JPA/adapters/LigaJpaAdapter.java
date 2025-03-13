package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.LigaJpaMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.LigaJpaRepository;
import com.adondeband.back_end_adonde_band.dominio.modelos.Liga;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.LigaPort;
import org.springframework.stereotype.Component;

@Component
public class LigaJpaAdapter implements LigaPort {

    private final LigaJpaRepository ligaJpaRepository;

    private final LigaJpaMapper ligaMapper;

    public LigaJpaAdapter(final LigaJpaRepository ligaJpaRepository, final LigaJpaMapper ligaMapper) {
        this.ligaJpaRepository = ligaJpaRepository;
        this.ligaMapper = ligaMapper;
    }

    @Override
    public Liga save(Liga liga) {
        return ligaMapper.toDomain(
                ligaJpaRepository.save(
                        ligaMapper.toEntity(liga)));
    }
}
