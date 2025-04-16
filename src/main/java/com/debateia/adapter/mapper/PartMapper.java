package com.debateia.adapter.mapper;

import com.debateia.adapter.in.web.dto.response.ParticipationResponseDTO;
import com.debateia.adapter.out.persistence.entities.ParticipationEntity;
import com.debateia.domain.Participation;

public class PartMapper {
    public static Participation toDomain(ParticipationEntity e) {
        Participation d = new Participation();
        d.setLeagueId(e.getLeagueId());
        d.setBotId(e.getBotId());
        d.setPoints(e.getPoints());
        d.setPosition(e.getPosition());
        
        return d;
    }
    
    public static ParticipationResponseDTO toDTO(Participation d) {
        ParticipationResponseDTO r = new ParticipationResponseDTO();
        r.setBotId(d.getBotId());
        r.setPoints(d.getPoints());
        r.setName(d.getName());
        r.setNDraws(d.getNDraws());
        r.setNWins(d.getNWins());
        r.setNLoses(d.getNLoses());
        
        return r;
    }
}
