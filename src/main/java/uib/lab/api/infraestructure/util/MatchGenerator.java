package uib.lab.api.infraestructure.util;

import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Round;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchGenerator {

    // Este método genera los enfrentamientos aleatorios entre los bots
    public List<Match> generateMatches(List<Bot> bots, League league) {
        List<Match> matches = new ArrayList<>();

        // Aleatoriza la lista de bots
        Collections.shuffle(bots);

        // Verifica si hay rondas en la liga
        List<Round> rounds = league.getRounds(); // Obtener las rondas de la liga

        if (rounds.isEmpty()) {
            throw new RuntimeException("No rounds available in the league to start matches.");
        }

        // Suponemos que el primer round se usará para estos enfrentamientos
        Round currentRound = rounds.get(0); // Obtén el primer round

        // Genera los enfrentamientos entre los bots
        for (int i = 0; i < bots.size(); i += 2) {
            if (i + 1 < bots.size()) {
                // Crea un nuevo enfrentamiento entre dos bots
                Match match = new Match();
                match.setBot1(bots.get(i));
                match.setBot2(bots.get(i + 1));
                match.setState(Match.MatchState.PENDING);
                match.setRound(currentRound); // Asocia el enfrentamiento con el round actual
                matches.add(match);
            }
        }

        return matches;
    }
}