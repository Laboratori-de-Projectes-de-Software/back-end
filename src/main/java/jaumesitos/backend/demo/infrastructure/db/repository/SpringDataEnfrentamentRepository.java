package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.EnfrentamentDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataEnfrentamentRepository extends JpaRepository<EnfrentamentDBO, Integer> {
    List<EnfrentamentDBO> findByLeagueId(int leagueId);

}