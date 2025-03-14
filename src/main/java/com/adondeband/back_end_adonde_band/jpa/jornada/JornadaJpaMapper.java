package com.adondeband.back_end_adonde_band.jpa.jornada;

import com.adondeband.back_end_adonde_band.dominio.Ids.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.Ids.JornadaId;
import com.adondeband.back_end_adonde_band.dominio.Ids.LigaId;
import com.adondeband.back_end_adonde_band.dominio.Ids.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.modelos.Jornada;
import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoEntity;
import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =   {
        EnfrentamientoJpaMapper.class,
        LigaJpaMapper.class,
}, componentModel = "spring")
public interface JornadaJpaMapper {
    JornadaJpaMapper INSTANCE = Mappers.getMapper(JornadaJpaMapper.class);

    // Mapea de JornadaEntity a Jornada
    Jornada toDomain(JornadaEntity jornadaEntity);

    // Mapea de Bot a BotEntity
    JornadaEntity toEntity(Jornada jornada);

    //TODO Terrorismo, va porque dios lo quiere
    default ParticipacionEntity map(ParticipacionId value) {
        return null;
    }

    // Mapeo de atributos
    default JornadaId toJornadaId(Long value) {
        return new JornadaId(value);
    }

    default long toJornadaIdint(JornadaId id) {
        return id.value();
    }

    default LigaId toLigaId(LigaEntity entity) {
        return new LigaId(entity.getId());
    }

    default EnfrentamientoId toEnfrentamientoId(EnfrentamientoEntity entity) {
        return new EnfrentamientoId(entity.getId());
    }
}
