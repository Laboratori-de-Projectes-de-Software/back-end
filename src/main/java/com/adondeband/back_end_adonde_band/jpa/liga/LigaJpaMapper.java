package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.jpa.entities.ESTADO_Entity;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =  {
            ParticipacionJpaMapper.class,
            ImagenJpaMapper.class
        }, componentModel = "spring")
public interface LigaJpaMapper {
    LigaJpaMapper INSTANCE = Mappers.getMapper(LigaJpaMapper.class);

    // Mapea de LigaEntity a Liga
    Liga toDomain(LigaEntity ligaEntity);

    // Mapea de Liga a LigaEntity
    LigaEntity toEntity(Liga liga);

    default long map(LigaId value){
        return value.value();
    }

    // Mapeo de atributos
    default LigaId toLigaId(long value) {
        return new LigaId(value);
    }

    default ParticipacionId toParticipacionId(ParticipacionEntity entity) {
        return new ParticipacionId(entity.getId());
    }

    default ParticipacionEntity map(ParticipacionId value){
        return new ParticipacionEntity(value.value());
    }

    default ESTADO toESTADO(ESTADO_Entity estado_entity) {

        if (estado_entity == null) {
            return null;
        }

        return ESTADO.valueOf(estado_entity.name());
    }
}
