package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Lliga;
import jaumesitos.backend.demo.infrastructure.res.dto.LligaDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LligaDTOMapper {
    Lliga toDomain(LligaDTO lliga);

    LligaDTO toDTO(Lliga lliga);
}
