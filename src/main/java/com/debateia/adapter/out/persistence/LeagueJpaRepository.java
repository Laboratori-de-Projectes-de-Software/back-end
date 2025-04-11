
package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
// Using Spring Data JPA to autogenerate query methods given this interface specification
public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, Integer> {

}