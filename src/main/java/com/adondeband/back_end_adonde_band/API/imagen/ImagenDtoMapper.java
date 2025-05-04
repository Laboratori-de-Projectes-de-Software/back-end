package com.adondeband.back_end_adonde_band.API.imagen;

import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImagenDtoMapper {

    ImagenDtoMapper INSTANCE = Mappers.getMapper(ImagenDtoMapper.class);

    // Mapear de DTO a Dominio
    Imagen toDomain(ImagenDTO imagenDTO);

    //Mappear de Dominio a DTO
    ImagenDTO toDTO(Imagen imagen);

}
