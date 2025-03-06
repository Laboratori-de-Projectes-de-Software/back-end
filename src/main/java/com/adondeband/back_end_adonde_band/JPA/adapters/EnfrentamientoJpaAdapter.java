package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.EnfrentamientoMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.EnfrentamientoJpaRepository;
import com.adondeband.back_end_adonde_band.dominio.modelos.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.EnfrentamientoPort;
import org.springframework.stereotype.Component;

@Component
public class EnfrentamientoJpaAdapter implements EnfrentamientoPort {
    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;
    private final EnfrentamientoMapper enfrentamientoMapper;

    public EnfrentamientoJpaAdapter(final EnfrentamientoJpaRepository enfrentamientoJpaRepository, final EnfrentamientoMapper enfrentamientoMapper) {
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
