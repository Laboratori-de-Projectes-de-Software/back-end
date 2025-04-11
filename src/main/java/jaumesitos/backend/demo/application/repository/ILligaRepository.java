package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;

import java.util.List;

//Interfície que defineix les funcions relacionades amb les lligues

public interface ILligaRepository {
    void postLliga(League lliga);

    List<League> getLeagues(Integer owner);
}
