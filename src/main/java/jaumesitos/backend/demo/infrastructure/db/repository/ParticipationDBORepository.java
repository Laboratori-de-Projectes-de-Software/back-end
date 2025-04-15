package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IParticipationRepository;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import jaumesitos.backend.demo.infrastructure.db.mapper.ParticipationDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationDBORepository implements IParticipationRepository {
    private final ParticipationDBOMapper mapper;
    private final SpringDataParticipationRepository repository;
    @Override
    public void postParticipation(Participation c) {
        ParticipationDBO dbo = mapper.toDBO(c);
        try {
            repository.save(dbo);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException("Classificació ja existent", e);
        }
    }

    @Override
    public List<Participation> getParticipations(Integer leagueId) {
        List<ParticipationDBO> llista = repository.findByLeagueid(leagueId);
        if(llista.isEmpty()){
            throw new DuplicateEntityException("No se encontraron participaciones para la liga con ID " + leagueId);
        }
        return mapper.toListDomain(llista);
    }
}
