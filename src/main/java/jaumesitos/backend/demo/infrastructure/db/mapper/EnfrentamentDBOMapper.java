package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.db.dbo.EnfrentamentDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnfrentamentDBOMapper {
    EnfrentamentDBO toDBO(Match enfrentament);
    Match toDomain(EnfrentamentDBO enfrentamentDBO);
}
