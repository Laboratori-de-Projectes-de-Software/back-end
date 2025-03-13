package com.adondeband.back_end_adonde_band.API.mappers;

import com.adondeband.back_end_adonde_band.API.DTO.ImagenDTO;
import com.adondeband.back_end_adonde_band.dominio.modelos.Imagen;
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
