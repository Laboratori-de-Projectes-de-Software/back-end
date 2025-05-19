package com.debateia.application.service;

import com.debateia.adapter.in.rest.league.State;
import com.debateia.application.ports.in.rest.BotUseCase;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.application.ports.out.bot_messaging.BotMessagingPort;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor

public class MatchService implements MatchUseCase {
    // PROMPT
    final String PROMPT = "eres un bot hecho para discutir";

    private final MatchRepository matchRepository;
    private final BotRepository botRepository;
    private final BotUseCase botService;
    // TODO: incluir interface
    private final BotMessagingPort botMessagingPort;

    
    @Override
    public List<Match> getMatchesByLeagueId(Integer leagueId) {
        return matchRepository.findByLeagueId(leagueId);
    }

    @Override
    public List<Match> createLeagueMatches(League league) {

        List<Match> matches;
        HashMap<Integer, String> names = new HashMap<>();
        
        for (Integer id : league.getBotIds()) {
            Bot bot = botRepository.findById(id).get();
            names.put(id, bot.getName());
        }

        matches = generateMatches(league.getBotIds(), names, league.getRounds());
        

        matches.forEach(match -> {
            match.setLeagueId(league.getLeagueId());
            match.setState(State.PENDING);
            match.setRoundNumber(league.getRounds());
            match.setResult(null);
            match.setMatchId(null);
        });
        matchRepository.saveAll(matches);
        return matches;
    }

    public void res(){
        botRepository.findById(1);
    }

    /**
     * Crea rounds Matches entre Bots repitiendo según el número de rounds.
     * Cada bot tendra rounds Matches.
     * El orden de local y visitante variará entre rounds dependiendo de si es una ronda par o impar.
     * @return Lista de Matches con SOLO bot1id, bot2id setteados
     */
    private List<Match> generateMatches(List<Integer> botIds, HashMap<Integer, String> botNames, int rounds) {

        int nbots = botIds.size();
        int matchesPerRound = (nbots * (nbots - 1))/2;

        List<Match> matches = new ArrayList<>(rounds * matchesPerRound);

        List<int[]> oddCombinations = new ArrayList<>();
        List<int[]> evenCombinations = new ArrayList<>();
        List<int[]> combinations = new ArrayList<>();

        for (int i = 0; i < nbots; i++) { // generar combinaciones de bots
            for (int j = i + 1; j < nbots; j++) {
                oddCombinations.add(new int[]{i, j});
                evenCombinations.add(new int[]{j, i});
            }
        }

        for (int i = 1; i <= rounds; i++) {
            if(i % 2 == 0){
                combinations.addAll(oddCombinations);
            } else {
                combinations.addAll(evenCombinations);
            }
        }

        for (int[] par : combinations) { // iterar combinaciones de Matches
            int botA = botIds.get(par[0]);
            int botB = botIds.get(par[1]);

            Match match = new Match();
            match.setBot1id(botA);
            match.setBot2id(botB);
            match.setFighters(List.of(botNames.get(botA), botNames.get(botB)));
            matches.add(match);
        }
        return matches;
    }

    @Override
    public Match startMatch(int matchId) {
        // obtener Match por ID
        Match match = getMatchById(matchId);

        // comprobar que el partido no haya sido iniciado
        if (match.getState() != State.PENDING)
            throw new RuntimeException("El partido ya fue iniciado. (Estado actual: " + match.getState() + ")");

        // cambiar estado a in process
        match.setState(State.IN_PROCESS);

        // actualizar match
        match = matchRepository.updateMatch(matchId, match);

        // obtener bot1
        Bot bot1 = botService.getBotById(match.getBot1id());

        // crear mensaje
        Messages msg = new Messages(PROMPT, LocalDateTime.now(), bot1.getId(), matchId);
        // enviar mensaje (se persistirá a través del BotMessagingPort)
        botMessagingPort.sendMessageToBot(msg, bot1.getEndpoint());

        // devolver match
        return match;
    }

    private Match getMatchById (Integer matchId) {
        // buscar match en la BD
        Optional <Match> matchFound = matchRepository.findById(matchId);
        if (matchFound.isEmpty())
            throw new EntityNotFoundException("Match con id " + matchId + " no encontrado");

        return matchFound.get();
    }
}
