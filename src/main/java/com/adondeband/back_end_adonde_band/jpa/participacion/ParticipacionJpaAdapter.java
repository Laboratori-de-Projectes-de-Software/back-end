package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionJpaAdapter implements ParticipacionPort {

    private final ParticipacionJpaMapper participacionMapper;
    private final ParticipacionJpaRepository participacionJpaRepository;

    public ParticipacionJpaAdapter(final ParticipacionJpaMapper participacionMapper, final ParticipacionJpaRepository participacionJpaRepository) {
        this.participacionMapper = participacionMapper;
        this.participacionJpaRepository = participacionJpaRepository;
    }

    @Override
    @Transactional
    public Participacion save(Participacion participacion) {
        ParticipacionEntity participacionEntity = participacionMapper.toEntity(participacion);
        ParticipacionEntity p = participacionJpaRepository.save(participacionEntity);
        Participacion p2 = participacionMapper.toDomain(p);

        return participacionMapper.toDomain(
                participacionJpaRepository.save(
                        participacionMapper.toEntity(participacion)));
    }

    @Override
    @Transactional
    public Participacion findById(ParticipacionId id) {
        ParticipacionEntity participacionEntity = participacionJpaRepository.findById(id.value()).orElse(null);
        return participacionMapper.toDomain(participacionEntity);
    }
}
