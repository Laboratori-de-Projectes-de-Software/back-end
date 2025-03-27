package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.domain.Lliga;
import jaumesitos.backend.demo.infrastructure.db.mapper.LLigaDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LligaDBORepository implements ILligaRepository {

    //falta implementar per la db
    //private final SpringDataLLigaRepository springdata;


    private final LLigaDBOMapper mapper;

    @Override
    public Lliga postLliga(Lliga lliga) {
        //return mapper.toLLiga(springdata.postUser(mapper.toDBO(lliga)));
        return null;
    }
}
