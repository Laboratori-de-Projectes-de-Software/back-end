package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RespostaDBOMapper {
    RespostaDBO toDBO(Resposta enfrentament);
    Resposta toDomain(RespostaDBO enfrentamentDBO);
}