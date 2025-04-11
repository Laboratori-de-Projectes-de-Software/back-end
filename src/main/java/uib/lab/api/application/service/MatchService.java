package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaEntity.Round;
import uib.lab.api.infraestructure.jpaRepositories.BotJpaRepository;
import uib.lab.api.infraestructure.jpaRepositories.RoundJpaRepository;
import uib.lab.api.infraestructure.util.ApiResponse;
import uib.lab.api.infraestructure.util.MatchGenerator;
import uib.lab.api.application.dto.league.LeagueResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final LeaguePort leaguePort;
    private final MatchPort matchPort;
    private final MatchGenerator matchGenerator;
    private final RoundJpaRepository roundRepository;
    private final BotJpaRepository botRepository;

    public ApiResponse<List<Match>> startLeague(int leagueId) {
        // Obtener la liga por su ID
        League league = leaguePort.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("League not found"));

        // Obtener los bots asociados a la liga
        List<Bot> bots = botRepository.findById(league.getBotIds());

        // Verificar si hay suficientes bots para iniciar la liga
        if (bots.size() < 2) {
            return new ApiResponse<>(400, "Not enough bots to start the league", null);
        }

        // Crear un nuevo round para la liga (si no tiene uno)
        Round round = new Round();
        round.setInitialDate(LocalDateTime.now()); // Fecha y hora de inicio
        round.setLeague(league); // Asociar el round con la liga
        roundRepository.save(round); // Guardar el round en la base de datos

        // Generar enfrentamientos aleatorios entre los bots
        List<Match> matches = matchGenerator.generateMatches(bots, league);

        // Asociar los enfrentamientos con el round creado
        for (Match match : matches) {
            match.setRound(round); // Asocia cada enfrentamiento al round
        }

        // Guardar los enfrentamientos generados en la base de datos
        matchPort.saveAll(matches);

        // Crear la respuesta
        LeagueResponseDTO response = new LeagueResponseDTO(
                league.getId(),
                league.getName(),
                league.getUrlImagen(),
                league.getUser().getName(), // Asumimos que la liga tiene un objeto User relacionado
                league.getNumRounds(),
                league.getPlayTime(),
                bots.size(), // Número de bots
                matches // Retornamos los matches generados
        );

        // Retornar la respuesta con los enfrentamientos creados
        return new ApiResponse<>(200, "League started and matches created", response);
    }
}
