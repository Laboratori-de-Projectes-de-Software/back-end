package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.infrastructure.res.dto.BotDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.BotResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotDTOMapper {
    Bot toDomain(BotDTO botDTO);
    BotResponseDTO toResponseDTO(Bot bot);
}
