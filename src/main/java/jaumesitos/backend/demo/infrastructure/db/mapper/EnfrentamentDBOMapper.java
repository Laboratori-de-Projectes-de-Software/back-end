package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.db.dbo.EnfrentamentDBO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnfrentamentDBOMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "botIdLocal", target = "idBotLocal"),
            @Mapping(source = "botIdVisitant", target = "idBotVisitant"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "result", target = "resultat")
    })
    Match toDomain(EnfrentamentDBO enfrentamentDBO);

    @InheritInverseConfiguration
    EnfrentamentDBO toDBO(Match enfrentament);
}
