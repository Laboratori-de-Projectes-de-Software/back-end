package com.debateia.adapter.mapper;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.in.web.dto.response.MatchResponseDTO;
import com.debateia.adapter.out.persistence.entities.MatchEntity;
import com.debateia.domain.Match;

public class MatchMapper {

    public static Match toDomain(MatchEntity entity) {
        Match dom = new Match();
        dom.setMatchId(entity.getId());
        dom.setResult(entity.getResult());
        dom.setState(State.valueOf(entity.getState()));
        dom.setRoundNumber(entity.getRoundNumber());
        if (entity.getBot1() != null) {
            dom.setBot1id(entity.getBot1().getId());
        }
        if (entity.getBot2() != null) {
            dom.setBot2id(entity.getBot2().getId());
        }
        if (entity.getLeague() != null) {
            dom.setLeagueId(entity.getLeague().getId());
        }
        return dom;
    }

    public static MatchEntity toEntity(Match dom) {
        MatchEntity entity = new MatchEntity();
        entity.setResult(dom.getResult());
        entity.setState(dom.getState().toString());
        entity.setRoundNumber(dom.getRoundNumber());
        entity.setId(dom.getMatchId());
        return entity;
    }

    public static MatchResponseDTO toResponseDTO(Match dom) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setMatchId(dom.getMatchId());
        dto.setState(dom.getState());
        dto.setResult(dom.getResult());
        dto.setRoundNumber(dom.getRoundNumber());
        return dto;
    }
}
