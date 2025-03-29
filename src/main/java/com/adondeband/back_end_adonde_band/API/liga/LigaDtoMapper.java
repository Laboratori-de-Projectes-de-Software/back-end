package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.imagen.ImagenDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses =  {ImagenDtoMapper.class})
public interface LigaDtoMapper {
    LigaDtoMapper INSTANCE = Mappers.getMapper(LigaDtoMapper.class);

    // Mapear de DTO a Dominio
    Liga toDomain(LigaDTO ligaDTO);

    // Mapea de Bot a BotDTO
    LigaDTO toDTO(Liga liga);
}
