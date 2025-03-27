package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IRespostaRepository;
import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.mapper.RespostaDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespostaDBORepository implements IRespostaRepository {
    //private final SpringDataRespostaRepository springDataRepository;
    private final RespostaDBOMapper mapper;

    @Override
    public void save(Resposta resposta) {
        //springDataRepository.save(mapper.toDBO(resposta));
    }

    @Override
    public Optional<Resposta> findById(String id) {
        //return springDataRepository.findById(id).map(mapper::toDomain);
        return null;
    }
}
