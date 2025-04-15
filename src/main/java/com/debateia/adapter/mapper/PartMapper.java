package com.debateia.adapter.mapper;

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
}
