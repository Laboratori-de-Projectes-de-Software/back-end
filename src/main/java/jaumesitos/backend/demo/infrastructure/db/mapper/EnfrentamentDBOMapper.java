package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Enfrentament;
import jaumesitos.backend.demo.infrastructure.db.dbo.EnfrentamentDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnfrentamentDBOMapper {
    EnfrentamentDBO toDBO(Enfrentament enfrentament);
    Enfrentament toDomain(EnfrentamentDBO enfrentamentDBO);
}
