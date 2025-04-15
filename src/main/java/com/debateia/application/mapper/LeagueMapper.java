package com.debateia.application.mapper;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.domain.League;
import java.util.stream.Collectors;

/**
 *
 * @author kjorda
 */
public class LeagueMapper {
    public LeagueResponseDTO toLeagueResponseDTO(League l) {
        LeagueResponseDTO d = new LeagueResponseDTO();
        d.setLeagueId(l.getLeagueId());
        d.setState(mapStateToEnum(l.getState()));
        d.setName(l.getName());
        d.setUrlImagen(l.getUrlImagen());
        d.setUser(l.getUserId());
        d.setRounds(l.getRounds());
        d.setMatchTime(l.getMatchTime());
        d.setBots(l.getBotIds());
        
        return d;
    }
    
    public League toDomain(LeagueEntity e) {
        League l = new League();
        l.setLeagueId(e.getLeagueId());
        l.setName(e.getName());
        l.setUrlImagen(e.getUrlImagen());
        l.setRounds(e.getRounds());
        l.setMatchTime(e.getMatchTime());
        l.setState(e.getState());
        l.setMatchIds(e.getMatches().stream()
                .map(elem -> elem.getId())
                .collect(Collectors.toList()));
        
        l.setBotIds(e.getLeague_bots().stream()
                .map(elem -> elem.getBotId())
                .collect(Collectors.toList()));
        
        l.setUserId(e.getUser().getId());
        
        return l;
    }
    
    
    private State mapStateToEnum(String state) {
        if (state == null) {
            return null;
        }
        try {
            return State.valueOf(state);
        } catch (IllegalArgumentException e) {
            
            return null;
        }
    }
}
