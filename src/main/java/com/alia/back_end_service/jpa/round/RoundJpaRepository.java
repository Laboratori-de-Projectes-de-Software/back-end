package com.alia.back_end_service.jpa.round;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundJpaRepository extends JpaRepository<RoundEntity, Integer> {
    void deleteRoundEntityById(Integer round_id);
}
