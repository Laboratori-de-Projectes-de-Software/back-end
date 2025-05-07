package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.res.dto.RespostaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RespostaDTOMapper {
    Resposta toDomain(RespostaDTO respostaDTO);
    RespostaDTO toDTO(Resposta resposta);
}
