
package com.debateia.adapter.out.league;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
// Using Spring Data JPA to autogenerate query methods given this interface specification
public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, Integer> {
    List<LeagueEntity> findByUser_Id(Integer userid);
}