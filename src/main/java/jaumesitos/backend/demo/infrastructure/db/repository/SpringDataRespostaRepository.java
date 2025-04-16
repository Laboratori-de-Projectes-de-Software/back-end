package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataRespostaRepository  extends JpaRepository<RespostaDBO, String> {
    List<RespostaDBO> findByMatchId(int matchId);

}