package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.db.dbo.LeagueDBO;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LLigaDBOMapper {
    LeagueDBO toDBO(League lliga);

    League toDomain(LeagueDBO lliga);
}
