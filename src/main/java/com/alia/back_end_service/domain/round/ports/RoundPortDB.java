package com.alia.back_end_service.domain.round.ports;

import com.alia.back_end_service.domain.round.Round;

import java.util.Optional;

public interface RoundPortDB {

    //Gets and Find
    Optional<Round> findById(Integer round_id);

    //Create
    Round saveRound(Round round);

    //Delete
    void deleteRound(Integer round_id);
}
