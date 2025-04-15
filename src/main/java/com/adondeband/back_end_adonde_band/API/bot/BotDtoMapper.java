package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.API.imagen.ImagenDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses =  {ImagenDtoMapper.class})
public interface BotDtoMapper {
    BotDtoMapper INSTANCE = Mappers.getMapper(BotDtoMapper.class);

    // Mapear de DTO a Dominio
    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "imagen", source = "urlImage")
    @Mapping(target = "cualidad", source = "description")
    @Mapping(target = "numVictorias", source = "NWins")
    @Mapping(target = "numEmpates", source = "NDraws")
    @Mapping(target = "numDerrotas", source = "NLosses")
    Bot toDomain(BotDTOResponse botDTO);

    // Mapea de Bot a BotDTO
    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "urlImage", source = "imagen")
    @Mapping(target = "description", source = "cualidad")
    @Mapping(target = "NWins", source = "numVictorias")
    @Mapping(target = "NDraws", source = "numEmpates")
    @Mapping(target = "NLosses", source = "numDerrotas")
    BotDTOResponse toDTO(Bot bot);

    // Mapeo de atributos
    default BotId toBotId(Long id) {
        return new BotId(id);}

    default Long toLong(BotId id) {
        if (id == null) {
            return null;
        }

        return id.value();}

    default String toImagenStr(Imagen img){
        return img.getRuta();
    }

    default Imagen toImagen(String str){
        return new Imagen(str);
    }
}
