package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.application.usecases.users.CreateLeague;
import com.example.gironetaServer.domain.League;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LeagueService implements CreateLeague {

    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public League createLeague(@org.jetbrains.annotations.NotNull League league) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        league.setUserId(usuario);
        return leagueRepository.save(league);
    }
}