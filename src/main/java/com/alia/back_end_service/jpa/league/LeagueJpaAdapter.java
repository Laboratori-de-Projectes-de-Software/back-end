package com.alia.back_end_service.jpa.league;


import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.League;


import java.util.List;



public class LeagueJpaAdapter implements LeaguePortDB {

    private final LeagueJpaRepository leagueJpaRepository;

    private final LeagueMapper leagueMapper;

    public LeagueJpaAdapter(LeagueJpaRepository repository, LeagueMapper mapper) {
        this.leagueJpaRepository = repository;
        this.leagueMapper = mapper;
    }

    @Override
    public League saveLeague(League league) {
        LeagueEntity entity = leagueMapper.toEntity(league);

        // Falta lo de las rondas

        LeagueEntity saved = leagueJpaRepository.save(entity);
        return leagueMapper.toDomain(saved);
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
    public List<League> getLeaguesByUser(String username) {
        return leagueJpaRepository.findLeaguesByUser(username).stream()
                .map(leagueMapper::toDomain)
                .toList();
    }
}
