package com.debateia.adapter.in.web;

import com.debateia.adapter.out.persistence.AIEntity;
import com.debateia.application.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class AIController {
    private final AIService aiService;

    @GetMapping
    public List<AIEntity> getAIs(@RequestParam(name = "owner", required = false) Integer ownerId) {
        return aiService.getAIs(Optional.ofNullable(ownerId));
    }

    /*
    @PostMapping
    public ResponseEntity<AIDTO> createAI(@RequestBody AIDTO dto) {
        AIDTO created = aiService.create(dto);
        return ResponseEntity.created(URI.create("/bot/" + created.getId())).body(created);
    }


    @PutMapping("/{botId}")
    public ResponseEntity<AIDTO> updateAI(@PathVariable Integer botId, @RequestBody AIDTO dto) {
        AIDTO updated = aiService.update(botId, dto);
        return ResponseEntity.ok(updated);
    }
    */
}