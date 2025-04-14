package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.BotDBO;
import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataClassificacioRepository extends JpaRepository<ParticipationDBO, Integer> {
    List<ParticipationDBO> findByLeagueid(Integer leagueid);
}
