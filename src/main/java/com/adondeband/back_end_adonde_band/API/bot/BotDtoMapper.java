package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.API.imagen.ImagenDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses =  {ImagenDtoMapper.class})
public interface BotDtoMapper {
    BotDtoMapper INSTANCE = Mappers.getMapper(BotDtoMapper.class);

    // Mapear de DTO a Dominio
    Bot toDomain(BotDTO botDTO);

    // Mapea de Bot a BotDTO
    BotDTO toDTO(Bot bot);

    // Mapeo de atributos
    default String toNombre(BotId id) {
        return id.value();
    }

    default BotId toBotId(String id) { return new BotId(id);}

    default String toImagenStr(Imagen img){
        return img.getRuta();
    }

    default Imagen toImagen(String str){
        return new Imagen(str);
    }
}
