package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.res.dto.MatchDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchDTOMapper {
    Match toDomain(MatchDTO matchDTO);
    MatchDTO toDTO(Match match);
}
