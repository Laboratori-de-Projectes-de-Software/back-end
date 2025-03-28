package com.alia.back_end_service.jpa.round;


import java.util.Optional;

public interface RoundJpaRepository {

    Optional<RoundEntity> findById(Integer roundId);
    RoundEntity save(RoundEntity entity);
    void deleteRound(Integer round_id);
}
