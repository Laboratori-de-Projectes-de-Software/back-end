package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.conversacion.ConversacionPort;
import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ConversacionJpaAdapter implements ConversacionPort {
    private final ConversacionJpaRepository conversacionJpaRepository;

    private final ConversacionJpaMapper conversacionMapper;

    public ConversacionJpaAdapter(final ConversacionJpaRepository conversacionJpaRepository, final ConversacionJpaMapper conversacionMapper) {
        this.conversacionJpaRepository = conversacionJpaRepository;
        this.conversacionMapper = conversacionMapper;
    }

    @Override
    @Transactional
    public Conversacion save(Conversacion conversacion) {
        return conversacionMapper.toDomain(
                conversacionJpaRepository.save(
                        conversacionMapper.toEntity(conversacion)));
    }
}
