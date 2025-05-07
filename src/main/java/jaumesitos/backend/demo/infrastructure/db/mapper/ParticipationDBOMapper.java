package jaumesitos.backend.demo.infrastructure.db.mapper;

import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationDBOMapper {
    ParticipationDBO toDBO(Participation c);
    Participation toDomain(ParticipationDBO dbo);
    List<Participation> toListDomain(List<ParticipationDBO> p);
}
