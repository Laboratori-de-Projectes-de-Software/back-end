package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.infrastructure.res.dto.ParticipationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationResponseDTOMapper {
    @Mapping(source = "botid", target = "botId")
    @Mapping(source = "wins", target = "nWins")
    @Mapping(source = "draws", target = "nDraws")
    @Mapping(source = "losses", target = "nLosses")
    ParticipationResponseDTO toDTO(Participation c);
    @Mapping(source = "botId", target = "botid")
    @Mapping(source = "nWins", target = "wins")
    @Mapping(source = "nDraws", target = "draws")
    @Mapping(source = "nLosses", target = "losses")
    Participation toDomain(ParticipationResponseDTO c);
    @Mapping(source = "botid", target = "botId")
    @Mapping(source = "wins", target = "nWins")
    @Mapping(source = "draws", target = "nDraws")
    @Mapping(source = "losses", target = "nLosses")
    List<ParticipationResponseDTO> toResponseDTO(List<Participation> p);
}
