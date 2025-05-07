package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;

import java.util.List;
import java.util.Optional;

//Interfície que defineix les funcions relacionades amb les lligues

public interface ILligaRepository {
    League postLliga(League lliga);

    List<League> getLeagues(Integer owner);

    Optional<League> findById(int id);

    Optional<League> deleteById(int id);
}
