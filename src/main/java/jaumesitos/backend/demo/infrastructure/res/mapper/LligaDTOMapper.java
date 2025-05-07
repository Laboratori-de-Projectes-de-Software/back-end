package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LligaDTOMapper {
    League toDomain(LeagueDTO lliga);

    LeagueDTO toDTO(League lliga);

    @Mapping(source = "userId", target = "user")
    LeagueResponseDTO toResponseDTO(League lliga);
    @Mapping(source = "userId", target = "user")
    List<LeagueResponseDTO> toResponseDTOList(List<League> lligues);
}
