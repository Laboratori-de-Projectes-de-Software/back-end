package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import jaumesitos.backend.demo.infrastructure.db.dbo.LeagueDBO;
import jaumesitos.backend.demo.infrastructure.db.mapper.LLigaDBOMapper;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LligaDBORepository implements ILligaRepository {

    //falta implementar per la db
    private final SpringDataLLigaRepository springdata;


    private final LLigaDBOMapper mapper;

    @Override
    public void postLliga(League lliga) {
        try {
            LeagueDBO dbo = mapper.toDBO(lliga);
            springdata.save(dbo);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException("Lliga ja existent", e);
        }catch(Exception error){
            throw error;
        }

    }
    @Override
    public List<League> getLeagues(Integer owner) {
        return springdata.findByUserId(owner).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
