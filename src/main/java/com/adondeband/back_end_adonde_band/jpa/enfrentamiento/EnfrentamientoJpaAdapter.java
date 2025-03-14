package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoPort;
import org.springframework.stereotype.Component;

@Component
public class EnfrentamientoJpaAdapter implements EnfrentamientoPort {
    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;
    private final EnfrentamientoJpaMapper enfrentamientoMapper;

    public EnfrentamientoJpaAdapter(final EnfrentamientoJpaRepository enfrentamientoJpaRepository, final EnfrentamientoJpaMapper enfrentamientoMapper) {
        this.enfrentamientoJpaRepository = enfrentamientoJpaRepository;
        this.enfrentamientoMapper = enfrentamientoMapper;
    }

    @Override
    public Enfrentamiento save(Enfrentamiento enfrentamiento) {
        return enfrentamientoMapper.toDomain(
                enfrentamientoJpaRepository.save(
                        enfrentamientoMapper.toEntity(enfrentamiento)));
    }
}
