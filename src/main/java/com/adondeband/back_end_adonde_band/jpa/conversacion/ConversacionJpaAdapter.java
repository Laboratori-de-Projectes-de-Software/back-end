package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.modelos.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.ConversacionPort;
import org.springframework.stereotype.Component;

@Component
public class ConversacionJpaAdapter implements ConversacionPort {
    private final ConversacionJpaRepository conversacionJpaRepository;

    private final ConversacionJpaMapper conversacionMapper;

    public ConversacionJpaAdapter(final ConversacionJpaRepository conversacionJpaRepository, final ConversacionJpaMapper conversacionMapper) {
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
