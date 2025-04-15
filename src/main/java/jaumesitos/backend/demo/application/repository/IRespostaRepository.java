package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Resposta;

import java.util.List;
import java.util.Optional;

public interface IRespostaRepository {
    List<Resposta> findByMatchId(int id);
    void save(Resposta resposta);
}
