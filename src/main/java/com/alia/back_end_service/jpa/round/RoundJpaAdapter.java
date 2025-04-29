package com.alia.back_end_service.jpa.round;


import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.round.Round;
import com.alia.back_end_service.domain.round.ports.RoundPortDB;
import com.alia.back_end_service.jpa.game.GameJpaRepository;
import com.alia.back_end_service.jpa.league.LeagueEntity;
import com.alia.back_end_service.jpa.league.LeagueJpaRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class RoundJpaAdapter implements RoundPortDB {
    private final RoundJpaRepository roundJpaRepository;
    private final LeagueJpaRepository leagueJpaRepository;
    private final GameJpaRepository gameJpaRepository;

    private final RoundMapper roundMapper;

    public RoundJpaAdapter(RoundJpaRepository roundJpaRepository, RoundMapper roundMapper, LeagueJpaRepository leagueJpaRepository, GameJpaRepository gameJpaRepository) {
        this.roundJpaRepository = roundJpaRepository;
        this.roundMapper = roundMapper;
        this.leagueJpaRepository = leagueJpaRepository;
        this.gameJpaRepository = gameJpaRepository;
    }

    @Override
    public Optional<Round> findById(Integer round_id) {
        Optional<RoundEntity> roundEntityOptional = roundJpaRepository.findById(round_id);
        return roundEntityOptional.map(roundMapper::toDomain);
    }

    @Override
    public Round saveRound(Round round) {
        LeagueEntity league = leagueJpaRepository.findById(round.getLeagueId()).orElse(null);
        RoundEntity roundEntity = roundMapper.toEntity(round);
        roundEntity.setLeague(league);
        RoundEntity savedEntity = roundJpaRepository.save(roundEntity);
        return roundMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteRound(Integer round_id) {
        roundJpaRepository.deleteRoundEntityById(round_id);
    }
}
