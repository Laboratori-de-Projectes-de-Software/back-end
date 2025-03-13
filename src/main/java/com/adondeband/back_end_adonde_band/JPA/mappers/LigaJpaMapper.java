package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.ESTADO_Entity;
import com.adondeband.back_end_adonde_band.JPA.entities.LigaEntity;
import com.adondeband.back_end_adonde_band.JPA.entities.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.dominio.Ids.LigaId;
import com.adondeband.back_end_adonde_band.dominio.Ids.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.modelos.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.modelos.Liga;
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

    // Mapeo de atributos
    default LigaId toLigaId(long value) {
        return new LigaId(value);
    }

    default ParticipacionId toParticipacionId(ParticipacionEntity entity) {
        return new ParticipacionId(entity.getId());
    }

    default ESTADO toESTADO(ESTADO_Entity estado_entity) {
        return ESTADO.valueOf(estado_entity.name());
    }
}
