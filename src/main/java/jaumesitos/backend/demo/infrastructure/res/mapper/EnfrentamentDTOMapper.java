package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Enfrentament;
import jaumesitos.backend.demo.infrastructure.res.dto.EnfrentamentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnfrentamentDTOMapper {
    Enfrentament toDomain(EnfrentamentDTO enfrentamentDTO);
    EnfrentamentDTO toDTO(Enfrentament enfrentament);
}
