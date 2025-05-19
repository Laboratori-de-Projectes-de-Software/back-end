package com.debateia.application.service;

import com.debateia.adapter.in.rest.league.State;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
    // TODO: incluir interface
    // private final BotMessagingPort botMessagingPort;
    // private final BotMessageReceiverPort botMessageReceiverPort;

    
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
        // Obtener Match por ID
        Match match = getMatch(matchId);

        // comprobar que el partido no haya sido iniciado
        if (match.getState() != State.PENDING)
            throw new RuntimeException("El partido ya fue iniciado. (Estado actual: " + match.getState() + ")");

        // cambiar estado a in process
        match.setState(State.IN_PROCESS);

        // Actualizar match
        matchRepository.updateMatch(matchId, match);

        // buscar bots
        Optional <Bot> bot1_found = botRepository.findById(match.getBot1id());
        if (bot1_found.isEmpty())
            throw new EntityNotFoundException("Bot con id " + match.getBot1id() + " no encontrado");

        Optional <Bot> bot2_found = botRepository.findById(match.getBot2id());
        if (bot2_found.isEmpty())
            throw new EntityNotFoundException("Bot con id " + match.getBot2id() + " no encontrado");

        Bot bot1 = bot1_found.get();
        Bot bot2 = bot2_found.get();

        // crear mensaje
        Messages msg = new Messages(PROMPT, LocalDateTime.now(), match.getBot1id(), matchId);
        // enviar mensaje (se persistirá a través del botPort
        // botMessagingPort.sendMessageBot(msg, bot1.getEndpoint());

        // recibir respuesta y reenviar al segundo bot
        mensajeSegundoBot(matchId, bot2);
        return match;
    }

    private Match getMatch (Integer matchId) {
        // buscar match en la BD
        Optional <Match> matchFound = matchRepository.findById(matchId);
        if (matchFound.isEmpty())
            throw new EntityNotFoundException("Match con id " + matchId + " no encontrado");

        return matchFound.get();
    }

    @Async
    protected void mensajeSegundoBot(Integer matchId, Bot bot2) {
        // recibir mensaje
        Messages msg_received = new Messages();     // suponiendo que es un parámetro de salida
        // botMessageReceiverPort.receiveBotMessage(msg_received);

        // enviar mensaje al segundo bot con el prompt y la respuesta del primer bot
        // crear Mensaje
        Messages msg_bot2 = new Messages(
                PROMPT + "\n\nLa siguiente es la respuesta de tu contrincante:\n\n" + msg_received.getContents(),
                LocalDateTime.now(),
                bot2.getId(),
                matchId);

        // enviar mensaje al bot2
        // botMessagingPort.sendMessageBot(msg_bot2, bot2.getEndpoint());
    }
}
