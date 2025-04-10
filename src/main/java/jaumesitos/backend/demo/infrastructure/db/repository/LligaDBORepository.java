package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.domain.Lliga;
import jaumesitos.backend.demo.infrastructure.db.mapper.LLigaDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LligaDBORepository implements ILligaRepository {

    //falta implementar per la db
    private final SpringDataLLigaRepository springdata;


    private final LLigaDBOMapper mapper;

    @Override
    public Lliga postLliga(Lliga lliga) {
        //return mapper.toLLiga(springdata.postUser(mapper.toDBO(lliga)));
        return null;
    }

    public Optional<Lliga> findById(int id) {
        return Optional.ofNullable(mapper.toLLiga(springdata.getLeagueById(id)));
    }

    public boolean deleteById(int id) {
        return springdata.deleteLeagueById(id);
    }
}
