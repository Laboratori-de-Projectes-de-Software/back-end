package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Classificacio;
import jaumesitos.backend.demo.infrastructure.res.dto.ClassificacioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassificacioDTOMapper {
    ClassificacioDTO toDTO(Classificacio c);
    Classificacio toDomain(ClassificacioDTO c);
}
