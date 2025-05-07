package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.MatchDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMatchRepository extends JpaRepository<MatchDBO, Integer> {
    List<MatchDBO> findByLeagueId(int leagueId);

}