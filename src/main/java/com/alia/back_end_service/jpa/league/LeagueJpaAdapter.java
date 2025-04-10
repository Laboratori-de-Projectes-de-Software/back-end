package com.alia.back_end_service.jpa.league;


import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.bot.BotJpaRepository;
import com.alia.back_end_service.jpa.bot.BotMapper;
import com.alia.back_end_service.jpa.game.GameEntity;
import com.alia.back_end_service.jpa.game.GameMapper;
import com.alia.back_end_service.jpa.user.UserEntity;
import com.alia.back_end_service.jpa.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class LeagueJpaAdapter implements LeaguePortDB {

    private final LeagueJpaRepository leagueJpaRepository;
    private final BotJpaRepository botJpaRepository;
    private final LeagueMapper leagueMapper;
    private final BotMapper botMapper;
    private final GameMapper gameMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public League saveLeague(League league) {
        LeagueEntity save = leagueMapper.toEntity(league);
        for (Integer i: league.getBotIds()) {
            Optional<BotEntity> botEntity = botJpaRepository.findById(i);
            botEntity.ifPresent(entity -> save.getBots().add(entity));
        }

        Optional <UserEntity> o = userJpaRepository.findById(league.getOwner());
        save.setOwner(o.get());
        return leagueMapper.toDomain(leagueJpaRepository.save(save));
    }

    @Override
    public League updateLeague(League league){
        LeagueEntity entity = leagueJpaRepository.findById(league.getId()).orElse(null);
        if(entity != null){
            entity.setName(league.getName()==null? entity.getName():league.getName());
            entity.setInit_time(league.getInit_time() == null? entity.getInit_time():league.getInit_time());
            entity.setEnd_time(league.getEnd_time() == null? entity.getEnd_time():league.getEnd_time());
            entity.setNumber_match(league.getNumber_match()==null? entity.getNumber_match():league.getNumber_match());
            entity.setTime_match(league.getTime_match() == null? entity.getTime_match():league.getTime_match());
            entity.setState(league.getState() == null? entity.getState():league.getState());
            return leagueMapper.toDomain(leagueJpaRepository.save(entity));
        }
        return null;
    }

    @Override
    public League inscribeBot(Integer league_id, Integer bot_id) {
        LeagueEntity entity = leagueJpaRepository.findById(league_id).orElse(null);
        BotEntity botEntity = botJpaRepository.findById(bot_id).orElse(null);
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
    public void deleteLeague(Integer id) {
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

    //Posible eliminación
    @Override
    public List<League> getLeaguesByUser(String username) {
        List<LeagueEntity> leagues = leagueJpaRepository.findLeaguesByBots_User_Username(username);
        return leagues.stream()
                .map(leagueMapper::toDomain)
                .toList();
    }

    @Override
    public List<League> getLeaguesByBotId(Integer botId) {
        return leagueJpaRepository.findLeaguesByBotId(botId).stream()
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

    @Override
    public boolean existLeagueIdWithBotId(Integer leagueId, Integer botId){
        return leagueJpaRepository.existsLeagueEntitiesByIdAndBots_Id(leagueId, botId);
    }

    @Override
    public List<Game> getAllLeagueGamesByLeagueId(Integer leagueId) {
        List<GameEntity> gameEntities = leagueJpaRepository.findAllGamesByLeagueId(leagueId);
        return gameEntities.stream()
                .map(gameMapper::toDomain)
                .toList();
    }

    @Override
    public List<League> findLeaguesByOwner_Username(String ownerUsername) {
        List<LeagueEntity> leaguesEntity = leagueJpaRepository.findLeaguesByOwner_Username(ownerUsername);
        return leaguesEntity.stream().map(leagueMapper::toDomain).toList();
    }
}
