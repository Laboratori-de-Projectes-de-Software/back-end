package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.db.dbo.LeagueDBO;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LLigaDBOMapper {
    LeagueDBO toDBO(League lliga);

    @Mapping(source = "id", target = "leagueId")
    League toDomain(LeagueDBO lliga);
}
