package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.ApiResponse;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeaguePort leaguePort;
    private final UserPort userPort;
    private final ModelMapper strictMapper;

    public ApiResponse<LeagueResponseDTO> createLeague(LeagueDTO leagueDTO) {
        try {

            userPort.findById(leagueDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + leagueDTO.getUserId()));

            var league = strictMapper.map(leagueDTO, LeagueDomain.class);
            league.setState(League.LeagueState.PENDING);

            LeagueDomain leagueDomain = leaguePort.save(league);

            LeagueResponseDTO leagueResponseDTO = new LeagueResponseDTO(
                    leagueDomain.getId(),
                    leagueDomain.getState().name(),
                    leagueDomain.getName(),
                    leagueDomain.getUrlImagen(),
                    leagueDomain.getPlayTime(),
                    leagueDomain.getNumRounds(),
                    leagueDomain.getUserId(),
                    leagueDomain.getBotIds()
            );

            return new ApiResponse<>(201, "League created", leagueResponseDTO);

        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "Not Found: Required entity not found");
        } catch (ResponseStatusException e) {
            return new ApiResponse<>(409, "Conflict: League already exists");
        } catch (Exception ex) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }
}