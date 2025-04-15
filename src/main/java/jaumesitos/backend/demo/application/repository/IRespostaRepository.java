package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;

import java.util.List;

public interface IRespostaRepository {
    List<RespostaDBO> findByMatchId(int id);
    void save(Resposta resposta);
}
