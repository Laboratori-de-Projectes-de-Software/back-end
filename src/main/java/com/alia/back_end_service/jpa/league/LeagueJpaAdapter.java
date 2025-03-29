package com.alia.back_end_service.jpa.league;


import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.bot.BotJpaRepository;
import com.alia.back_end_service.jpa.bot.BotMapper;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


@Component
public class LeagueJpaAdapter implements LeaguePortDB {

    private final LeagueJpaRepository leagueJpaRepository;
    private final BotJpaRepository botJpaRepository;
    private final LeagueMapper leagueMapper;
    private final BotMapper botMapper;

    public LeagueJpaAdapter(LeagueJpaRepository repository, LeagueMapper mapper, BotJpaRepository botJpaRepository, BotMapper botMapper) {
        this.leagueJpaRepository = repository;
        this.leagueMapper = mapper;
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
    }

    @Override
    public League saveLeague(League league) {
        LeagueEntity entity = leagueMapper.toEntity(league);

        // Falta lo de las rondas

        LeagueEntity saved = leagueJpaRepository.save(entity);
        return leagueMapper.toDomain(saved);
    }

    @Override
    public League inscribeBot(Integer league_id, String bot_name) {
        LeagueEntity entity = leagueJpaRepository.findById(league_id).orElse(null);
        BotEntity botEntity = botJpaRepository.findById(bot_name).orElse(null);
        if (entity == null) {
            return null;
        }
        if (botEntity == null) {
            return null;
        }
        entity.getBots().add(botEntity);
        leagueJpaRepository.save(entity);
        return leagueMapper.toDomain(entity);
    }

    @Override
    public void deleteLeague(Long id) {
        leagueJpaRepository.deleteById(id);
    }

    @Override
    public List<League> getAllLeagues() {
        return leagueJpaRepository.findAll().stream()
                .map(leagueMapper::toDomain)
                .toList();
    }

    @Override
    public League getLeague(Integer id) {
        Optional<LeagueEntity> entity = leagueJpaRepository.findById(id);
        return entity.map(leagueMapper::toDomain).orElse(null);
    }

    @Override
    public List<League> getLeaguesByUser(String username) {
        return leagueJpaRepository.findLeaguesByUser(username).stream()
                .map(leagueMapper::toDomain)
                .toList();
    }

    @Override
    public List<Bot> getAllLeagueBots(Integer leagueId) {
        Optional<LeagueEntity> entity = leagueJpaRepository.findById(leagueId);
        if (entity.isPresent()) {
            LeagueEntity leagueEntity = entity.get();
            return leagueEntity.getBots().stream().map(botMapper::toDomain).toList();
        }

        return null;
    }
}
