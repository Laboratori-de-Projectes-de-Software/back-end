package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.ConversacionMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.ConversacionJpaRepository;
import com.adondeband.back_end_adonde_band.dominio.modelos.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.ConversacionPort;
import org.springframework.stereotype.Component;

@Component
public class ConversacionJpaAdapter implements ConversacionPort {
    private final ConversacionJpaRepository conversacionJpaRepository;

    private final ConversacionMapper conversacionMapper;

    public ConversacionJpaAdapter(final ConversacionJpaRepository conversacionJpaRepository, final ConversacionMapper conversacionMapper) {
        this.conversacionJpaRepository = conversacionJpaRepository;
        this.conversacionMapper = conversacionMapper;
    }

    @Override
    public Conversacion save(Conversacion conversacion) {
        return conversacionMapper.toDomain(
                conversacionJpaRepository.save(
                        conversacionMapper.toEntity(conversacion)));
    }
}
