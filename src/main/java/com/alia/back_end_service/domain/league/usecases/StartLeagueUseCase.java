package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.game.ports.GamePortDB;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.ports.LeagueStartPortAPI;
import com.alia.back_end_service.domain.round.Round;
import com.alia.back_end_service.domain.round.ports.RoundPortDB;
import lombok.AllArgsConstructor;

import java.util.*;

class MatchPair {
    public final int bot1;
    public final int bot2;

    public MatchPair(int bot1, int bot2) {
        if (bot1 < bot2) {
            this.bot1 = bot1;
            this.bot2 = bot2;
        } else {
            this.bot1 = bot2;
            this.bot2 = bot1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchPair)) return false;
        MatchPair that = (MatchPair) o;
        return bot1 == that.bot1 && bot2 == that.bot2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bot1, bot2);
    }
}


@AllArgsConstructor
public class StartLeagueUseCase implements LeagueStartPortAPI {
    private final LeaguePortDB leaguePortDB;
    private final RoundPortDB roundPortDB;
    private final GamePortDB gamePortDB;

    @Override
    public void startLeague(Integer leagueId) {
        League league = leaguePortDB.getLeague(leagueId);
        int rounds = league.getNumber_match();
        List<Integer> bots = new ArrayList<>(league.getBotIds());

        List<MatchPair> possibleMatches = new ArrayList<>();
        Set<MatchPair> usedMatches = new HashSet<>();

        // Generar todas las combinaciones únicas
        for (int i = 0; i < bots.size(); i++) {
            for (int j = i + 1; j < bots.size(); j++) {
                possibleMatches.add(new MatchPair(bots.get(i), bots.get(j)));
            }
        }

        Collections.shuffle(possibleMatches); // Para que los enfrentamientos sean aleatorios

        int matchesPerRound = bots.size() / 2;
        Stack<MatchPair> pos = new Stack<>();
        pos.addAll(possibleMatches);


        MatchPair matchPair;
        Round round;
        Game game;
        for (int i = 0; i < rounds; i++) {
            round = new Round();
            round.setNumber_round(i+1);
            round.setState("En espera");
            round.setLeagueId(leagueId);
            round = roundPortDB.saveRound(round);

            //Falta revisar si el mismo bot juega otra vez en la misma ronda
            boolean [] used = new boolean[bots.size()];
            for (int j = 0; j < matchesPerRound; j++) {
                matchPair = pos.getFirst();
                game = new Game();
                if (used[matchPair.bot1-1] || used[matchPair.bot2-1]) {
                    pos.push(matchPair);
                    continue;
                }
                used[matchPair.bot1-1] = true;
                used[matchPair.bot2-1] = true;
                game.setBot_local_id(matchPair.bot1);
                game.setBot_visit_id(matchPair.bot2);
                game.setState("En espera");
                game.setRoundId(round.getId());

                game = gamePortDB.saveGame(game);
                round.getGameIds().add(game.getId());
            }

            roundPortDB.saveRound(round);
        }

        league.setState("Iniciado");
        leaguePortDB.saveLeague(league);
    }

}
