package com.example.demo.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.application.port.in.LeagueUseCase;
import com.example.demo.application.port.out.LeagueRepository;
import com.example.demo.domain.model.League;
import com.example.demo.dtos.LeagueDTO;

/**
 * Implementación de los casos de uso para la gestión de ligas.
 */
@Service
public class LeagueServiceImpl implements LeagueUseCase {

    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public LeagueDTO createLeague(LeagueDTO leagueDTO) {
        League league = new League(leagueDTO.getName());
        League savedLeague = leagueRepository.save(league);
        return toDTO(savedLeague);
    }

    @Override
    public LeagueDTO updateLeague(Long leagueId, LeagueDTO leagueDTO) {
        League existingLeague = leagueRepository.findById(leagueId);
        existingLeague.updateLeagueName(leagueDTO.getName());
        League updatedLeague = leagueRepository.save(existingLeague);
        return toDTO(updatedLeague);
    }

    @Override
    public LeagueDTO getLeague(Long leagueId) {
        League league = leagueRepository.findById(leagueId);
        return toDTO(league);
    }

    @Override
    public List<LeagueDTO> listLeagues() {
        return leagueRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private LeagueDTO toDTO(League league) {
        LeagueDTO dto = new LeagueDTO();
        dto.setId(league.getId());
        dto.setName(league.getName());
        dto.setCreatedAt(league.getCreatedAt());
        return dto;
    }
}
