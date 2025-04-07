package uib.lab.api.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import uib.lab.api.application.dto.league.LeagueRequest;
import uib.lab.api.infraestructure.entity.League;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;
import uib.lab.api.infraestructure.util.ApiMessage;
import uib.lab.api.infraestructure.util.message.MessageCode;
import uib.lab.api.infraestructure.util.message.MessageConverter;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueJpaRepository leagueRepository;
    private final MessageConverter messageConverter;
    private final ModelMapper strictMapper;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        CREATED("league.created");
        private final String code;
    }


    public ApiMessage createLeague(LeagueRequest leagueRequest, Locale locale) {

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
