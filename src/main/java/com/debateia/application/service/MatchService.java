package com.debateia.application.service;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public List<Match> getMatchesByLeagueId(Integer leagueId) {
        return matchRepository.findByLeagueId(leagueId);
    }

    public List<Match> createLeagueMatches(League league) {
        List<Match> matches = new ArrayList<>(league.getRounds());
        matches = generarCombatesBalanceados(matches, league.getBotIds(), league.getRounds());

        // @TODO cuando se use la tabla tbl_league_bots, consultarla y pasar por parametro a la funcion anterior para hacer setFighters
        /* @TODO
        * @TODO
        * @TODO
        * @TODO
        * */

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
    private List<Match> generarCombatesBalanceados(List<Match> matches, List<Integer> botIds, int rounds) {
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
                // @TODO setFighters
                matches.add(match);

                combatesPorBot.put(botA, combatesPorBot.get(botA) - 1); // reducir combates restantes
                combatesPorBot.put(botB, combatesPorBot.get(botB) - 1);

                if (matches.size() == rounds) break; // suficientes Match generados
            }
        }
        return matches;
    }
}
