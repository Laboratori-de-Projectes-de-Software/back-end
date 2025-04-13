package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.dto.match.MatchResponseDTO;
import uib.lab.api.application.mapper.implementations.LeagueMapperImpl;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import uib.lab.api.application.port.*;
import uib.lab.api.domain.*;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.util.ApiResponse;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final LeaguePort leaguePort;
    private final BotPort botPort;
    private final MatchPort matchPort;
    private final MatchMapper matchMapper;
    private final RoundPort roundPort;
    private final RoundService roundService;

    public ApiResponse<MatchResponseDTO> getMatchesByLeague(int id) {
        try {
            List<MatchResponseDTO> matchesList;

            // Obtener la liga por ID
            LeagueDomain league = leaguePort.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + id));

            // Obtener todos los partidos de la liga
            List<MatchDomain> matches = matchPort.findAllByLeague(league);

            // Crear lista de IDs de bots para hacer la consulta
            List<Integer> botIds = matches.stream()
                    .flatMap(match -> Stream.of(match.getBotId1(), match.getBotId2()))
                    .collect(Collectors.toList());

            // Obtener los bots desde la base de datos
            List<BotDomain> bots = botPort.findAllByIdIn(botIds);

            // Crear un mapa de botId -> nombre del bot
            Map<Integer, String> botNamesMap = bots.stream()
            .collect(Collectors.toMap(BotDomain::getId, BotDomain::getName));

            // Mapear los partidos a la respuesta, con los nombres de los bots
            matchesList = matches.stream()
                    .map(match -> new MatchResponseDTO(
                            match.getId(),
                            match.getState(),
                            match.getResult(),
                            match.getRounds(),
                            new String[] {
                                    botNamesMap.getOrDefault(match.getBotId1(), "Unknown Bot"),
                                    botNamesMap.getOrDefault(match.getBotId2(), "Unknown Bot")
                            }))
                    .collect(Collectors.toList());

            // Verificar si se encontraron partidos y devolver la respuesta
            if (!matchesList.isEmpty()) {
                return new ApiResponse(200, "Matches found", matchesList);
            } else {
                return new ApiResponse<>(404, "No matches found");
            }
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "Matches not found");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

    public void createMatches(LeagueDomain league) {
        int[] botIds = league.getBotIds();

        if (botIds == null || botIds.length < 2) {
            throw new IllegalArgumentException("Not enough bots to create matches");
        }

        List<Integer> botList = new ArrayList<>();
        for (int botId : botIds) {
            botList.add(botId);
        }

        Collections.shuffle(botList, new Random());

        List<MatchDomain> matches = new ArrayList<>();

        for (int i = 0; i < botList.size(); i++) {
            for (int j = i + 1; j < botList.size(); j++) {
                MatchDomain match = new MatchDomain();
                match.setBotId1(botList.get(i));
                match.setBotId2(botList.get(j));
                match.setState(Match.MatchState.PENDING);
                match.setResult(null);
                match.setRounds(league.getNumRounds());
                matches.add(match);
            }
        }

        List<RoundDomain> rounds = roundService.createRounds(league);

        int roundIndex = 0;
        for (MatchDomain match : matches) {
            RoundDomain assignedRound = rounds.get(roundIndex);
            match.setRoundId(assignedRound.getId());
            roundIndex = (roundIndex + 1) % rounds.size();
        }

        matchPort.saveAll(matches);
    }
}