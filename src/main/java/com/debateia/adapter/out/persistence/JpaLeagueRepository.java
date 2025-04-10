package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.application.ports.out.persistence.MatchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
public class JpaLeagueRepository implements MatchRepository {
    public interface LeagueRepo extends JpaRepository<LeagueEntity, Integer> {
    
    }

}
