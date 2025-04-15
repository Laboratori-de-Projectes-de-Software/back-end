package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IRespostaRepository;
import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.mapper.RespostaDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespostaDBORepository implements IRespostaRepository {

    private final SpringDataRespostaRepository springDataRespostaRepository;

    @Qualifier("respostaDBOMapper")
    private final RespostaDBOMapper mapper;

    @Override
    public List<Resposta> findByMatchId(String id) {
        return springDataRespostaRepository.findByMatchId(id);
    }

    @Override
    public void save(Resposta resposta) {
        springDataRespostaRepository.save(mapper.toDBO(resposta));
    }
}
