package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.ImagenEntity;
import com.adondeband.back_end_adonde_band.dominio.modelos.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImagenJpaMapper {
    ImagenJpaMapper INSTANCE = Mappers.getMapper(ImagenJpaMapper.class);

    // Mapea de ImagenEntity a Imagen
    Imagen toDomain(ImagenEntity imagenEntity);

    // Mapea de Imagen a ImagenEntity
    ImagenEntity toEntity(Imagen imagen);
}
