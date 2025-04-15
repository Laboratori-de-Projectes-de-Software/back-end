package com.debateia.application.mapper;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.adapter.in.web.dto.request.LeagueDTO;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.domain.League;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author kjorda
 */
public class LeagueMapper {
    public static LeagueResponseDTO toLeagueResponseDTO(League l) {
        LeagueResponseDTO d = new LeagueResponseDTO();
        d.setLeagueId(l.getLeagueId());
        d.setState(mapStateToEnum(l.getState()));
        d.setName(l.getName());
        d.setUrlImagen(l.getUrlImagen());
        //if (l.getUserId() != null)
            d.setUser(l.getUserId());
        d.setRounds(l.getRounds());
        d.setMatchTime(l.getMatchTime());
        d.setBots(l.getBotIds());
        
        return d;
    }
    
    public static League toDomain(LeagueDTO d) {
        League l = new League();
        l.setName(d.getName());
        l.setUrlImagen(d.getUrlImagen());
        l.setRounds(d.getRounds());
        l.setMatchTime(d.getMatchTime());
        l.setBotIds(d.getBots());
        l.setState("PENDING");

        return l;
    }
    
    public static League toDomain(LeagueEntity e) {
        League l = new League();
        l.setLeagueId(e.getId());
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
    
    public static LeagueEntity toEntity(League l) {
        LeagueEntity e = new LeagueEntity();
        e.setName(l.getName());
        e.setUrlImagen(l.getUrlImagen());
        e.setRounds(l.getRounds());
        e.setMatchTime(l.getMatchTime());
        
        if (l.getUserId() == null) 
            e.setUser(null);
        else {
            UserEntity dummy = new UserEntity();
            dummy.setId(l.getUserId());
            e.setUser(dummy);
        }
        
        e.setMatches(new ArrayList<>());
        e.setLeague_bots(new ArrayList<>());
        e.setState(l.getState());
        
        // TODO: Añadir records de league_bots basandonos en la lista de botids del dominio
        
        return e;
    }
    
    
    private static State mapStateToEnum(String state) {
//        if (state == null)
//            return null;
        
        try {
            return State.valueOf(state);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
