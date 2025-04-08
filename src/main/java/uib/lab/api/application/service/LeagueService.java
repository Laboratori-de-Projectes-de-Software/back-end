package uib.lab.api.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import uib.lab.api.application.dto.league.LeagueRequest;
import uib.lab.api.domain.entity.League;
import uib.lab.api.domain.entity.User;
import uib.lab.api.infraestructura.jpaRepositories.LeagueJpaRepository;
import uib.lab.api.infraestructura.util.ApiMessage;
import uib.lab.api.infraestructura.util.message.MessageCode;
import uib.lab.api.infraestructura.util.message.MessageConverter;
import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class LeagueService {

    @Autowired
    private Validator validator;

    private final LeagueJpaRepository leagueRepository;
    private final MessageConverter messageConverter;
    private final ModelMapper strictMapper;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        CREATED("league.created"),
        NOT_NAME("league.not_name_field"),
        NOT_PLAYTIME("league.not_playtime_field"),
        NOT_ROUNDS("league.not_rounds_field");

        private final String code;
    }

    public ApiMessage createLeague(LeagueRequest leagueRequest, Locale locale) {
        Set<ConstraintViolation<LeagueRequest>> violations = validator.validate(leagueRequest);
        if(!violations.isEmpty()){ //Hay fallos en la llamada, faltan campos
            if(leagueRequest.getName() == null || leagueRequest.getName().trim().isEmpty()){
                return  ApiMessage.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(messageConverter.getMessage(Message.NOT_NAME, Set.of(leagueRequest.getName()), locale))
                .build();
            }else if(leagueRequest.getNumRounds() == 0){
                return  ApiMessage.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(messageConverter.getMessage(Message.NOT_ROUNDS, Set.of(leagueRequest.getName()), locale))
                .build();
            }else if(leagueRequest.getPlayTime() == 0){
                return  ApiMessage.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(messageConverter.getMessage(Message.NOT_PLAYTIME, Set.of(leagueRequest.getName()), locale))
                .build();
            }
        }

        
        //Mapeamos campos y seteamos liga a PENDING
        var league = strictMapper.map(leagueRequest, League.class);
        league.setState(League.LeagueState.PENDING);

        
        //Guaramos en la base de datos
        leagueRepository.save(league);
        
        //Devolvemos mensaje
        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.CREATED, Set.of(leagueRequest.getName()), locale))
                .build();
    }
}
