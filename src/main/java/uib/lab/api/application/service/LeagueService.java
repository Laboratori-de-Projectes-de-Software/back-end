package uib.lab.api.application.service;

import com.sun.source.tree.Tree;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.dto.league.ParticipationResponseDTO;
import uib.lab.api.application.mapper.implementations.LeagueMapperImpl;
import uib.lab.api.application.port.*;
import uib.lab.api.domain.*;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.util.ApiResponse;

import java.util.*;
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
    private final BotPort botPort;

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
                return new ApiResponse(200, "No leagues found", new ArrayList<>());
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
                    return new ApiResponse(201, "Bot already in league");
                }
            }
            int[] newBotIds = new int[botIds.length + 1];

            System.arraycopy(botIds, 0, newBotIds, 0, botIds.length);
            newBotIds[newBotIds.length - 1] = botId;

            leagueDomain.setBotIds(newBotIds);

            leaguePort.save(leagueDomain);

            return new ApiResponse(201, "Bot registered");
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
                    .orElseThrow(() -> new IllegalArgumentException("League not found"));

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

    public ApiResponse<List<ParticipationResponseDTO>> getLeaderboardById(Integer leagueId) {
        try {
            LeagueDomain league = leaguePort.findById(leagueId)
                    .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + leagueId));

            List<MatchDomain> matches = matchPort.findAllByLeague(league);
            int nMatches = matches.size();

            int[] botIds = league.getBotIds();
            int nBots = botIds.length;

            HashMap<Integer, Integer> scores = new HashMap<>();
            HashMap<Integer, Integer> wins = new HashMap<>();
            HashMap<Integer, Integer> draws = new HashMap<>();
            HashMap<Integer, Integer> loses = new HashMap<>();

            for (int i = 0; i < nBots; ++i) {
                scores.put(botIds[i], 0);
                wins.put(botIds[i], 0);
                draws.put(botIds[i], 0);
                loses.put(botIds[i], 0);
            }

            for (int i = 0; i < nMatches; ++i) {
                MatchDomain match = matches.get(i);

                if (match.getState() != Match.MatchState.COMPLETED)
                    continue;

                int idLocal = match.getBotId1();
                int idVisiting = match.getBotId2();
                Match.MatchResult result = match.getResult();

                if (result == Match.MatchResult.LOCAL) {
                    scores.put(idLocal, scores.get(idLocal) + 3);
                    wins.put(idLocal, wins.get(idLocal) + 1);
                    loses.put(idVisiting, loses.get(idVisiting) + 1);
                } else if (result == Match.MatchResult.VISITING) {
                    scores.put(idVisiting, scores.get(idVisiting) + 3);
                    loses.put(idLocal, loses.get(idLocal) + 1);
                    wins.put(idVisiting, wins.get(idVisiting) + 1);
                } else {
                    scores.put(idLocal, scores.get(idLocal) + 1);
                    scores.put(idVisiting, scores.get(idVisiting) + 1);
                    draws.put(idLocal, draws.get(idLocal) + 1);
                    draws.put(idVisiting, draws.get(idVisiting) + 1);
                }
            }
            TreeMap<Integer, Integer> botsSorted = new TreeMap<>();

            for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
                botsSorted.put(entry.getValue(), entry.getKey());
            }
            int lastScore = -1, lastPos = -1, position = 0;
            List<ParticipationResponseDTO> ans = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : botsSorted.descendingMap().entrySet()) {
                int score = entry.getKey();
                int botId = entry.getValue();

                BotDomain bot = botPort.findById(botId)
                        .orElseThrow(() -> new RuntimeException("Bot not found"));

                ParticipationResponseDTO dto = new ParticipationResponseDTO();
                dto.setName(bot.getName());
                dto.setBotId(botId);
                if (score != lastScore) {
                    dto.setPosition(position);
                    lastPos = position;
                }else {
                    dto.setPosition(lastPos);
                }
                dto.setPoints(score);
                dto.setNWins(wins.get(botId));
                dto.setNDraws(draws.get(botId));
                dto.setNLoses(loses.get(botId));

                ans.add(dto);

                lastScore = score;
                ++position;
            }
            return new ApiResponse<>(200, "Classification found", ans);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "League not found");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }
}