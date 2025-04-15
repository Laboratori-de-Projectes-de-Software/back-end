package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.imagen.ImagenDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses =  {ImagenDtoMapper.class})
public interface LigaDtoMapper {
    LigaDtoMapper INSTANCE = Mappers.getMapper(LigaDtoMapper.class);

    @Mapping(target = "imagen", source = "urlImagen")
    // Mapear de DTO a Dominio
    Liga toDomain(LigaResponseDTO ligaDTO);

    @Mapping(target = "urlImagen", source = "imagen")
    // Mapea de Bot a BotDTO
    LigaResponseDTO toDTO(Liga liga);

    default long toId(LigaId id) {
        return id.value();
    }
    default LigaId toLigaId(int id) { return new LigaId(id);}

    default String toImagenStr(Imagen img){
        return img.getRuta();
    }

    default Imagen toImagen(String str){
        return new Imagen(str);
    }
}
