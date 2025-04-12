package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.application.service.LeagueService;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import uib.lab.api.application.service.ChatService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/chat")
//@PreAuthorize("isAuthenticated()")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{matchId}")
    public ApiResponse getAllMessages(@PathVariable int matchId){
        return chatService.getAllMessages(matchId);
    }
    
}
