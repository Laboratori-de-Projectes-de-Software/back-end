package com.debateia.adapter.in.web;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.domain.League;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Handles creating matches, etc...
 * @author kjorda
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueUseCase leagueUseCase;
    private final LeagueMapper leagueMapper;
    
    @GetMapping("/league/{id}")
    public LeagueResponseDTO getLeague(@PathVariable Integer id) {
        League lg = leagueUseCase.getLeague(id);
        return leagueMapper.toLeagueResponseDTO(lg);
    }
}