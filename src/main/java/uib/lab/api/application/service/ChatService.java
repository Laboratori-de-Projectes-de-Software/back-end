package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.dto.chat.ChatResponseDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.league.LeagueResponseDTO;
import uib.lab.api.application.mapper.implementations.ChatMapperImpl;
import uib.lab.api.application.mapper.implementations.LeagueMapperImpl;
import uib.lab.api.application.port.ChatPort;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.util.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatPort chatPort;
    private final MatchPort matchPort;
    private final ChatMapperImpl chatMapper;

    public ApiResponse<List<ChatResponseDTO>> getAllMessages(Integer id){
        try{
            List<ChatResponseDTO> chatList;
            if(id != null){
                MatchDomain match = matchPort.findById(id).orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + id));
                
                chatList = chatPort.findAllByMatch(match)
                .stream()
                .map(chat -> new ChatResponseDTO(  
                chat.getText(),
                chat.getTime(),
                -1))
                .collect(Collectors.toList());
            }else{
                chatList = chatPort.findAllChats()
                .stream()
                .map(chat -> new ChatResponseDTO(  
                chat.getText(),
                chat.getTime(),
                -1))
                .collect(Collectors.toList());
            }


            if(!chatList.isEmpty()){
                return new ApiResponse(200, "Chats found", chatList);
            } else {
                return new ApiResponse(404, "No Chats found");
            }

        } catch (IllegalArgumentException e) {
            return new ApiResponse(404, "Chats not found");
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error: " + e.getMessage());
        }
    }    

    
}
