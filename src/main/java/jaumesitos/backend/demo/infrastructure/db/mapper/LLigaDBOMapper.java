package jaumesitos.backend.demo.infrastructure.db.mapper;

import org.mapstruct.Mapper;
import jaumesitos.backend.demo.infrastructure.db.dbo.LligaDBO;
import jaumesitos.backend.demo.domain.Lliga;

@Mapper(componentModel = "spring")
public interface LLigaDBOMapper {
    LligaDBO toDBO(Lliga lliga);

    Lliga toLLiga(LligaDBO lliga);
}
