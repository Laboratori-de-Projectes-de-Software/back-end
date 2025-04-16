package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataParticipationRepository extends JpaRepository<ParticipationDBO, Integer> {
    List<ParticipationDBO> findByLeagueid(Integer leagueid);
}
