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

    // Mapear de DTO a Dominio
    @Mapping(target="id", source = "leagueId")
    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "imagen", source = "urlImagen")
    @Mapping(target = "rondas", source = "rounds")
    Liga toDomain(LigaResponseDTO ligaDTO);

    // Mapea de Bot a BotDTO
    @Mapping(target = "leagueId", source = "id")
    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "state", source = "estado")
    @Mapping(target = "urlImagen", source = "imagen")
    @Mapping(target = "rounds", source = "rondas")
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
