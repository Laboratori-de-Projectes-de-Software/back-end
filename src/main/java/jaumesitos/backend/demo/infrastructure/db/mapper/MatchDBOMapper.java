package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.db.dbo.MatchDBO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MatchDBOMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "botIdLocal", target = "idBotLocal"),
            @Mapping(source = "botIdVisitant", target = "idBotVisitant"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "result", target = "resultat")
    })
    Match toDomain(MatchDBO enfrentamentDBO);

    @InheritInverseConfiguration
    MatchDBO toDBO(Match enfrentament);
}
