package com.debateia.adapter.out.persistence;

import com.debateia.application.ports.out.persistence.AIRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kjorda
 */


@Repository
public class JpaAIRepository implements AIRepository{
    public interface AIRepo extends JpaRepository<AIEntity, Integer> {
    
    }
    
    
}
