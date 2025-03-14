package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaPort;
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
