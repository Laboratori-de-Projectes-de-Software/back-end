package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.ParticipacionMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.ParticipacionJpaRepository;
import com.adondeband.back_end_adonde_band.dominio.modelos.Participacion;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.ParticipacionPort;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionJpaAdapter implements ParticipacionPort {

    private final ParticipacionMapper participacionMapper;
    private final  ParticipacionJpaRepository participacionJpaRepository;

    public ParticipacionJpaAdapter(final ParticipacionMapper participacionMapper, final ParticipacionJpaRepository participacionJpaRepository) {
        this.participacionMapper = participacionMapper;
        this.participacionJpaRepository = participacionJpaRepository;
    }

    @Override
    public Participacion save(Participacion participacion) {
        return participacionMapper.toDomain(
                participacionJpaRepository.save(
                        participacionMapper.toEntity(participacion)));
    }
}
