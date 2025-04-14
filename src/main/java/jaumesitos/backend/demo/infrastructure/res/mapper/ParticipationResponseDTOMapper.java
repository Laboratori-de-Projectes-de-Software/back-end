package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.infrastructure.res.dto.ParticipationResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationResponseDTOMapper {
    ParticipationResponseDTO toDTO(Participation c);
    Participation toDomain(ParticipationResponseDTO c);
    List<ParticipationResponseDTO> toResponseDTO(List<Participation> p);
}
