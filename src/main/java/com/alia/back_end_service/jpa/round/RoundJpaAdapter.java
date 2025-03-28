package com.alia.back_end_service.jpa.round;


import com.alia.back_end_service.domain.round.Round;
import com.alia.back_end_service.domain.round.ports.RoundPortDB;


import java.util.Optional;

public class RoundJpaAdapter implements RoundPortDB {
    private final RoundJpaRepository roundJpaRepository;

    private final RoundMapper roundMapper;

    public RoundJpaAdapter(RoundJpaRepository roundJpaRepository, RoundMapper roundMapper) {
        this.roundJpaRepository = roundJpaRepository;
        this.roundMapper = roundMapper;
    }

    @Override
    public Optional<Round> findById(Integer round_id) {
        Optional<RoundEntity> roundEntityOptional = roundJpaRepository.findById(round_id);
        return roundEntityOptional.map(roundMapper::toDomain);
    }

    @Override
    public Round saveRound(Round round) {
        RoundEntity savedEntity = roundJpaRepository.save(roundMapper.toEntity(round));
        return roundMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteRound(Integer round_id) {
        roundJpaRepository.deleteRound(round_id);
    }
}
