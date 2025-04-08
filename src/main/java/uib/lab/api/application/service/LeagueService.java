package uib.lab.api.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.message.MessageCode;
import uib.lab.api.infraestructure.util.message.MessageConverter;

import javax.validation.Validator;
import uib.lab.api.application.errorHandler.*;
@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeaguePort leaguePort;
    private final MessageConverter messageConverter;
    private final ModelMapper strictMapper;

    @Autowired
    private Validator validator;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        CREATED("league.created");
        private final String code;
    }

    public Object createLeague(LeagueDTO leagueDTO, Locale locale) {
        //Comprobamos si faltan campos obligatorios
        LeagueErrorHandler fieldErrorHandler = new LeagueErrorHandler(leagueDTO, locale, messageConverter, validator);
        fieldErrorHandler.checkFieldViolations();

        if(fieldErrorHandler.hasError()){
            return fieldErrorHandler.getApiMessageError();
        }

        //Mapeamos campos y seteamos liga a PENDING
        var league = strictMapper.map(leagueDTO, LeagueDomain.class);
        league.setState(League.LeagueState.PENDING);

        
        //Guaramos en la base de datos
        LeagueDomain leagueDomain = leaguePort.save(league);
        
        //Devolvemos la liga
        return new LeagueResponseDTO(
                leagueDomain.getId(),
                leagueDomain.getState().name(),
                leagueDomain.getName(),
                leagueDomain.getUrlImagen(),
                leagueDomain.getPlayTime(),
                leagueDomain.getNumRounds(),
                leagueDomain.getUserId(),
                leagueDomain.getBotIds()
        );
    }


}
