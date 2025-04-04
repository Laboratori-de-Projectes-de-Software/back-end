package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.domain.League;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
    Optional<League> findById(Long id);

    List<League> findByUserId(Long userId);

    League save(League league);

    void deleteById(Long id);
}