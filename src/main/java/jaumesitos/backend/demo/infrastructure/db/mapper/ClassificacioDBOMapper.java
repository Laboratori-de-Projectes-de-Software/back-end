package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Classificacio;
import jaumesitos.backend.demo.infrastructure.db.dbo.ClassificacioDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassificacioDBOMapper {
    ClassificacioDBO toDBO(Classificacio c);
    Classificacio toDomain(ClassificacioDBO dbo);
}
