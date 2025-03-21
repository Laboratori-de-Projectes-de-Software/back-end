package com.debateia.adapter.out.persistence;

import com.debateia.application.ports.out.persistence.MatchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
public class JpaMatchRepository implements MatchRepository {
    public interface MatchRepo extends JpaRepository<MatchEntity, Integer> {
    
    }

}
