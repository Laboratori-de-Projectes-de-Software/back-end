package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author kjorda
 */

@Component
@RequiredArgsConstructor
public class JpaLeagueRepository implements LeagueRepository {
    
    // Using Spring Data JPA to autogenerate query methods given this interface specification
    public interface LeagueRepo extends JpaRepository<LeagueEntity, Integer> {
        
    }
    
    private final LeagueRepo leagueRepo;
    
    @Override
    public LeagueEntity findByLeagueId(int leagueId) {
        return leagueRepo.findById(leagueId).orElse(null);
    }

}
