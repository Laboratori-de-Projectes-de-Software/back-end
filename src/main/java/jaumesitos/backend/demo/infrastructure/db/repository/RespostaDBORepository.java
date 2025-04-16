package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IRespostaRepository;
import jaumesitos.backend.demo.domain.Resposta;
import java.util.stream.Collectors;

import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;
import jaumesitos.backend.demo.infrastructure.db.mapper.RespostaDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaDBORepository implements IRespostaRepository {

    private final SpringDataRespostaRepository springDataRespostaRepository;

    @Qualifier("respostaDBOMapper")
    private final RespostaDBOMapper mapper;

    @Override
    public List<Resposta> findByMatchId(int id) {
        return springDataRespostaRepository.findByMatchId(id)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Resposta resposta) {
        springDataRespostaRepository.save(mapper.toDBO(resposta));
    }
}
