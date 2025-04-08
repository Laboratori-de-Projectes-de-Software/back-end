package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.infrastructure.res.dto.BotDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.BotResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BotDTOMapper {
    Bot toDomain(BotDTO botDTO);
    BotResponseDTO toResponseDTO(Bot bot);
    List<BotResponseDTO> toResponseDTOList(List<Bot> bots);
}
