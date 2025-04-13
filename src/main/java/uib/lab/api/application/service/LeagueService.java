package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.mapper.implementations.LeagueMapperImpl;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.*;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.ApiResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeaguePort leaguePort;
    private final UserPort userPort;
    private final LeagueMapperImpl leagueMapper;
    private final MatchService matchService;
    private final RoundService roundService;
    private final MatchPort matchPort;
    private final RoundPort roundPort;

    public ApiResponse<LeagueResponseDTO> createLeague(LeagueDTO leagueDTO) {
        try {

            userPort.findById(leagueDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + leagueDTO.getUserId()));

            LeagueDomain domain = leagueMapper.toDomain(leagueDTO);

            LeagueDomain leagueDomain = leaguePort.save(domain);

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


    public ApiResponse<List<LeagueResponseDTO>> getAllLeagues(Integer owner) {
        try {
            List<LeagueResponseDTO> leagueList;
            if (owner != null) {
                UserDomain user = userPort.findById(owner)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + owner));

                leagueList = leaguePort.findAllByUser(user)
                        .stream()
                        .map(league -> new LeagueResponseDTO(
                                league.getId(),
                                league.getState().name(),
                                league.getName(),
                                league.getUrlImagen(),
                                league.getPlayTime(),
                                league.getNumRounds(),
                                league.getUserId(),
                                league.getBotIds()))
                        .collect(Collectors.toList());

            } else {
                leagueList = leaguePort.findAllLeagues()
                        .stream()
                        .map(league -> new LeagueResponseDTO(
                                league.getId(),
                                league.getState().name(),
                                league.getName(),
                                league.getUrlImagen(),
                                league.getPlayTime(),
                                league.getNumRounds(),
                                league.getUserId(),
                                league.getBotIds()))
                        .collect(Collectors.toList());
            }

            if (!leagueList.isEmpty()) {
                return new ApiResponse(200, "Leagues found", leagueList);
            } else {
                return new ApiResponse(404, "No leagues found");
            }
        } catch (IllegalArgumentException e) {
            return new ApiResponse(404, "Leagues not found");
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error");
        }
    }


    public ApiResponse<LeagueResponseDTO> getLeagueById(Integer leagueId) {
        try {
            LeagueDomain league = leaguePort.findById(leagueId)
                    .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + leagueId));

            LeagueResponseDTO leagueResponseDTO = new LeagueResponseDTO(
                    league.getId(),
                    league.getState().name(),
                    league.getName(),
                    league.getUrlImagen(),
                    league.getPlayTime(),
                    league.getNumRounds(),
                    league.getUserId(),
                    league.getBotIds());

            return new ApiResponse<>(200, "League found", leagueResponseDTO);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "League not found");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

    public ApiResponse addBotById(Integer leagueId, Integer botId) {
        try {
            LeagueDomain leagueDomain = leaguePort.findById(leagueId)
                    .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + leagueId));

            int[] botIds = leagueDomain.getBotIds();

            for (int i = 0; i < botIds.length; ++i) {
                if (botIds[i] == botId) {
                    return new ApiResponse(201, "League added");
                }
            }
            int[] newBotIds = new int[botIds.length + 1];

            for (int i = 0; i < botIds.length; ++i) {
                newBotIds[i] = botIds[i];
            }
            newBotIds[newBotIds.length - 1] = botId;

            leagueDomain.setBotIds(newBotIds);

            leagueDomain = leaguePort.save(leagueDomain);

            return new ApiResponse(201, "League added");
        } catch (IllegalArgumentException e) {
            return new ApiResponse(404, "League not found");
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error");
        }
    }
  
    public ApiResponse<LeagueResponseDTO> updateLeague(int leagueId, LeagueDTO dto) {
        try {
            LeagueDomain league = leaguePort.findById(leagueId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found"));

            league.setName(dto.getName());
            league.setUrlImagen(dto.getUrlImagen());
            league.setPlayTime(dto.getMatchTime());
            league.setNumRounds(dto.getRounds());
            league.setUserId(dto.getUserId());
            league.setBotIds(dto.getBots());

            LeagueDomain updatedLeague = leaguePort.save(league);

            LeagueResponseDTO responseDTO = new LeagueResponseDTO(
                    updatedLeague.getId(),
                    updatedLeague.getState().name(),
                    updatedLeague.getName(),
                    updatedLeague.getUrlImagen(),
                    updatedLeague.getPlayTime(),
                    updatedLeague.getNumRounds(),
                    updatedLeague.getUserId(),
                    updatedLeague.getBotIds()
            );

            return new ApiResponse<>(200, "League updated", responseDTO);
        } catch (ResponseStatusException e) {
            return new ApiResponse<>(404, "League not found");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

    public ApiResponse<LeagueResponseDTO> startLeague(int leagueId) {
        try {
            LeagueDomain league = leaguePort.findById(leagueId)
                    .orElseThrow(() -> new RuntimeException("League not found"));

            if (league.getState() == League.LeagueState.IN_PROGRESS) {
                return new ApiResponse<>(400, "League already started", null);
            }

            league.setState(League.LeagueState.IN_PROGRESS);
            leaguePort.save(league);

            matchService.createMatches(league);

            LeagueResponseDTO responseDTO = new LeagueResponseDTO(
                    league.getId(),
                    league.getState().name(),
                    league.getName(),
                    league.getUrlImagen(),
                    league.getPlayTime(),
                    league.getNumRounds(),
                    league.getUserId(),
                    league.getBotIds()
            );

            return new ApiResponse<>(201, "League started", responseDTO);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "League not found");
        } catch (SecurityException e) {
            return new ApiResponse<>(403, "Forbidden");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

    public ApiResponse<LeagueResponseDTO> deleteLeague(int leagueId) {
        try {
            Optional<LeagueDomain> optionalLeague = leaguePort.findById(leagueId);
            if (optionalLeague.isEmpty()) {
                return new ApiResponse<>(404, "League not found");
            }

            LeagueDomain league = optionalLeague.get();

            if (league.getState() == League.LeagueState.IN_PROGRESS) {
                return new ApiResponse<>(400, "Cannot delete a league that is in progress");
            }

            LeagueResponseDTO deletedLeagueDTO = new LeagueResponseDTO(
                    league.getId(),
                    league.getState().name(),
                    league.getName(),
                    league.getUrlImagen(),
                    league.getPlayTime(),
                    league.getNumRounds(),
                    league.getUserId(),
                    league.getBotIds()
            );

            // Eliminar matches asociados
            List<MatchDomain> matches = matchPort.findAllByLeague(league);
            matchPort.deleteAll(matches);

            // Eliminar rounds asociados
            List<RoundDomain> rounds = roundPort.findAllByLeagueId(leagueId);
            roundPort.deleteAll(rounds);

            // Eliminar la liga
            leaguePort.delete(league);

            return new ApiResponse<>(200, "League deleted", deletedLeagueDTO);

        } catch (SecurityException e) {
            return new ApiResponse<>(403, "You don't have permission to delete this league");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }
}