package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Resposta;
import java.util.Optional;

public interface IRespostaRepository {
    void save(Resposta resposta);
    Optional<Resposta> findById(String id);
}
