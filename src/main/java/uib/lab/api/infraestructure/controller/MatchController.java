package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.infraestructure.util.ApiResponse;
import uib.lab.api.application.service.ChatService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/match")
@PreAuthorize("isAuthenticated()")
public class MatchController {

    private final ChatService chatService;

    @GetMapping("/{matchId}/message")
    public ApiResponse getAllMessages(@PathVariable int matchId){
        return chatService.getAllMessages(matchId);
    }
    
}
