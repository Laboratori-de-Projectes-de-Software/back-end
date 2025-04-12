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
import uib.lab.api.application.port.BotPort;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.ApiResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final LeaguePort leaguePort;
    private final BotPort botPort;
    private final MatchPort matchPort;

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
}
