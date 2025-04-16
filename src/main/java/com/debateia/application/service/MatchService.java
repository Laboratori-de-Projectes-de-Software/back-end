package com.debateia.application.service;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final BotRepository botRepository;

    public List<Match> getMatchesByLeagueId(Integer leagueId) {
        return matchRepository.findByLeagueId(leagueId);
    }

    public List<Match> createLeagueMatches(League league) {
        List<Match> matches = new ArrayList<>(league.getRounds());
        List<String> names = new ArrayList<>(league.getBotIds().size());
        
        for (Integer id : league.getBotIds()) { // TODO: COMPROBAR SI FUNCIONA
            Bot bot = botRepository.findById(id).get();
            names.add(bot.getName());
        }
        
        matches = generarCombatesBalanceados(matches, league.getBotIds(), names, league.getRounds());
        

        matches.forEach(match -> {
            match.setLeagueId(league.getLeagueId());
            match.setState(State.PENDING);
            match.setRoundNumber(league.getRounds());
            match.setResult(null);
            match.setMatchId(null);
        });
        return matchRepository.saveAll(matches);
    }

    /**
     * Crea rounds Matches entre Bots sin repeticion de forma balanceada.
     * La maxima diferencia de matches entre bots es 1
     *
     * Cada bot tendra rounds/nbots Matches como minimo, y los rounds%nbots sobrantes (si hay)
     * se reparten aleatoriamente entre los bots.
     * @return Lista de Matches con SOLO bot1id, bot2id setteados
     */
    private List<Match> generarCombatesBalanceados(List<Match> matches, List<Integer> botIds, List<String> botNames, int rounds) {
        int nbots = botIds.size();
        int maxMatchesPorBot = rounds / nbots;
        int extraMatches = rounds % nbots;

        Map<Integer, Integer> combatesPorBot = new HashMap<>(); // limite de combates
        for (Integer id : botIds) {
            combatesPorBot.put(id, maxMatchesPorBot);
        }

        List<Integer> shuffledIds = new ArrayList<>(botIds); // combates extras
        Collections.shuffle(shuffledIds);
        for (int i = 0; i < extraMatches; i++) {
            combatesPorBot.put(shuffledIds.get(i), combatesPorBot.get(shuffledIds.get(i)) + 1);
        }

        List<int[]> combinaciones = new ArrayList<>();
        for (int i = 0; i < nbots; i++) { // generar combinaciones de bots
            for (int j = i + 1; j < nbots; j++) {
                combinaciones.add(new int[]{botIds.get(i), botIds.get(j)});
            }
        }
        Collections.shuffle(combinaciones); // barajar combinaciones

        for (int[] par : combinaciones) { // iterar combinaciones de Matches
            int botA = par[0];
            int botB = par[1];
            if (combatesPorBot.get(botA) > 0 && combatesPorBot.get(botB) > 0) { // no pasarse de Matches por Bot
                Match match = new Match();
                match.setBot1id(botA);
                match.setBot2id(botB);
                match.setFighters(List.of(botNames.get(botA), botNames.get(botB)));
                matches.add(match);

                combatesPorBot.put(botA, combatesPorBot.get(botA) - 1); // reducir combates restantes
                combatesPorBot.put(botB, combatesPorBot.get(botB) - 1);

                if (matches.size() == rounds) break; // suficientes Match generados
            }
        }
        return matches;
    }
}
