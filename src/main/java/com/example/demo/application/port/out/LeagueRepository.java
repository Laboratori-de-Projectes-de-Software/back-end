package com.example.demo.application.port.out;

import com.example.demo.domain.model.League;

import java.util.List;

/**
 * Define las operaciones para guardar y obtener ligas.
 */
public interface LeagueRepository {
    League save(League league);
    League findById(Long leagueId);
    List<League> findAll();
}
