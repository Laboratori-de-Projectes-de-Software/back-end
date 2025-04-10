package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.mapper.implementations.LeagueMapperImpl;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeaguePort leaguePort;
    private final UserPort userPort;
    private final LeagueMapperImpl leagueMapper;


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
        try{
            List<LeagueResponseDTO> leagueList;
            if (owner != null){
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

            }else{
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
}