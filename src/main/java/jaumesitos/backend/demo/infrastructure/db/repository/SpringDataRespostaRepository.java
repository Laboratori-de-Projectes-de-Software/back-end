package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataRespostaRepository  {
    List<Resposta> findByMatchId(String id);
    void save(RespostaDBO dbo);
}