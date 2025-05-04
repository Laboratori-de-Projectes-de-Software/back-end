package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoPort;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.conversacion.ConversacionJpaMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnfrentamientoJpaAdapter implements EnfrentamientoPort {
    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;
    private final EnfrentamientoJpaMapper enfrentamientoMapper;
    private final LigaJpaMapper ligaJpaMapper;
    private final ConversacionJpaMapper conversacionJpaMapper;

    public EnfrentamientoJpaAdapter(EnfrentamientoJpaRepository enfrentamientoJpaRepository, EnfrentamientoJpaMapper enfrentamientoMapper, LigaJpaMapper ligaJpaMapper, ConversacionJpaMapper conversacionJpaMapper) {
        this.enfrentamientoJpaRepository = enfrentamientoJpaRepository;
        this.enfrentamientoMapper = enfrentamientoMapper;
        this.ligaJpaMapper = ligaJpaMapper;
        this.conversacionJpaMapper = conversacionJpaMapper;
    }


    @Override
    @Transactional
    public Enfrentamiento save(Enfrentamiento enfrentamiento) {
        /*
        // DEBUG
        EnfrentamientoEntity enfrentamientoEntity = enfrentamientoMapper.toEntity(enfrentamiento);
        EnfrentamientoEntity enfrentamientoSaved = enfrentamientoJpaRepository.save(enfrentamientoEntity);
        Enfrentamiento enfrentamientoDomain = enfrentamientoMapper.toDomain(enfrentamientoSaved);

        return enfrentamientoDomain;
        */

        return enfrentamientoMapper.toDomain(
                enfrentamientoJpaRepository.save(
                        enfrentamientoMapper.toEntity(enfrentamiento)));
    }

    @Override
    @Transactional
    public List<Enfrentamiento> findById(Long idPartido) {
        return  enfrentamientoJpaRepository.findById(idPartido)
                .stream()
                .map(enfrentamientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Enfrentamiento> findEnfrentamientosByLiga(Liga liga) {

        LigaEntity ligaEntity = ligaJpaMapper.toEntity(liga);

        Sort sort = Sort.by(Sort.Direction.ASC, "ronda");
        List<EnfrentamientoEntity> enfrentamientoEntities = enfrentamientoJpaRepository.findByLiga(ligaEntity, sort);

        return enfrentamientoEntities
                .stream()
                .map(enfrentamientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion) {
        // Buscar Enfrentamiento
        List<EnfrentamientoEntity> enfrentamientosFound = enfrentamientoJpaRepository.findById(enfrentamientoId.value());

        // Obtener enfrentamiento y actualizar conversación
        EnfrentamientoEntity enfrentamientoEntity = enfrentamientosFound.getFirst();
        enfrentamientoEntity.setConversacion(conversacionJpaMapper.toEntity(conversacion));

        // Guardar el enfrentamiento actualizado en la base de datos
        return enfrentamientoMapper.toDomain(enfrentamientoJpaRepository.save(enfrentamientoEntity));
    }
}
