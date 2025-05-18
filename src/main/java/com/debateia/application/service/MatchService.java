package com.debateia.application.service;

import com.debateia.adapter.in.rest.league.State;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.application.ports.out.persistence.*;
import com.debateia.domain.Bot;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import com.debateia.domain.Participation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor

public class MatchService implements MatchUseCase {
    private final MatchRepository matchRepository;
    private final BotRepository botRepository;
    private final LeagueRepository leagueRepository;
    private final MessageRepository messageRepository;
    private final ParticipationRepository participationRepository;
    // Token placeholders
    private final static String TOKEN_LOCAL_WIN = "LOCAL";
    private final static String TOKEN_VISITING_WIN = "VISITING";
    private final static String TOKEN_DRAW = "DRAW";
    private final static Set<String> VALID_END_TOKENS = Set.of(TOKEN_LOCAL_WIN, TOKEN_VISITING_WIN, TOKEN_DRAW);
    private final static int POINTS_WIN = 3;
    private final static int POINTS_DRAW = 1;
    private final static int POINTS_LOSE = 0;

    @Override
    public List<Match> getMatchesByLeagueId(Integer leagueId) {
        return matchRepository.findByLeagueId(leagueId);
    }

    @Override
    public boolean isMatchFinished(Integer matchId, String token) {
        if (token != null && VALID_END_TOKENS.contains(token)) { // placeholder
            return true;
        }
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match con ID \""+matchId+"\" no encontrado"));
        long n_messages = messageRepository.countByMatchId(match.getMatchId());
        League league = leagueRepository.findById(match.getLeagueId())
                .orElseThrow(() -> new EntityNotFoundException("League con ID \""+match.getLeagueId()+"\" no encontrado"));
        return n_messages >= league.getMatchMaxMessages();
    }

    @Override
    public void finalizeMatch(Integer matchId, String token, Integer botId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match con ID \""+matchId+"\" no encontrado"));
        Participation participation1 = participationRepository.findById(match.getLeagueId(), match.getBot1id())
                .orElseThrow(() -> new EntityNotFoundException("Participation de bot "+match.getBot1id()
                        +" en liga " +match.getLeagueId()+" no encontrado"));
        Participation participation2 = participationRepository.findById(match.getLeagueId(), match.getBot2id())
                .orElseThrow(() -> new EntityNotFoundException("Participation de bot "+match.getBot2id()
                        +" en liga " +match.getLeagueId()+" no encontrado"));

        boolean isSenderLocal = (match.getBot1id().equals(botId)); // asume bot1id es el local
        Participation sender = isSenderLocal ? participation1 : participation2;
        Participation other = isSenderLocal ? participation2 : participation1;
        int result = 0;
        switch (token) {
            case TOKEN_LOCAL_WIN:
                applyWinLoss(sender, other, isSenderLocal);
                result = 1;
                break;
            case TOKEN_VISITING_WIN:
                applyWinLoss(sender, other, !isSenderLocal);
                result = -1;
                break;
            case TOKEN_DRAW:
                applyDraw(sender, other);
        }
        match.setResult(result);
        participationRepository.save(sender);
        participationRepository.save(other);
        match.setState(State.COMPLETED);
        matchRepository.save(match);
    }

    private void applyWinLoss(Participation sender, Participation other, boolean senderWon) {
        if (senderWon) {
            sender.setNWins(sender.getNWins()+1);
            sender.setPoints(sender.getPoints()+POINTS_WIN);
            other.setNLoses(other.getNLoses()+1);
            other.setPoints(other.getPoints()+POINTS_LOSE);
        } else {
            sender.setNLoses(sender.getNLoses()+1);
            sender.setPoints(sender.getPoints()+POINTS_LOSE);
            other.setNWins(other.getNWins()+1);
            other.setPoints(other.getPoints()+POINTS_WIN);
        }
    }

    private void applyDraw(Participation sender, Participation other) {
        sender.setNDraws(sender.getNDraws()+1);
        sender.setPoints(sender.getPoints()+POINTS_DRAW);
        other.setNDraws(other.getNDraws()+1);
        other.setPoints(other.getPoints()+POINTS_DRAW);
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
            Bot botA = botRepository.findById(botIds.get(par[0])).get();
            Bot botB = botRepository.findById(botIds.get(par[1])).get();

            Match match = new Match();
            match.setBot1id(botA.getId());
            match.setBot2id(botB.getId());
            match.setFighters(List.of(botA, botB));
            matches.add(match);
        }
        return matches;
    }
}
