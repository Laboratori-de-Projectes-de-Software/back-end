package org.example.backend.databaseapi.application.dto.partida;

import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface PartidaDTOMapper {

    @Mapping(target = "state", source = "estado")
    @Mapping(target = "roundNumber", source = "")
    @Mapping(target = "result", source = "")
    @Mapping(target = "fighters", source = "")
    MatchDTOResponse toMatchDTOResponse(Partida partida);

    default String toString(Estado estado){
        return estado.toString();
    }
}
