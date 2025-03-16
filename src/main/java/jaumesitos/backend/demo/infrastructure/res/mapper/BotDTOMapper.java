package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.infrastructure.res.dto.BotDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotDTOMapper {
    Bot toDomain(BotDTO botDTO);
    BotDTO toDTO(Bot bot);
}
