package com.debateia.adapter.in.web;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.service.BotService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {
    private final BotService botService;

    @GetMapping
    public List<BotEntity> getBots(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        return botService.getBots(Optional.ofNullable(ownerId));
    }

    /*
     * @PostMapping
     * public ResponseEntity<AIDTO> createAI(@RequestBody AIDTO dto) {
     * AIDTO created = aiService.create(dto);
     * return ResponseEntity.created(URI.create("/bot/" +
     * created.getId())).body(created);
     * }
     * 
     * 
     * @PutMapping("/{botId}")
     * public ResponseEntity<AIDTO> updateAI(@PathVariable Integer
     * botId, @RequestBody AIDTO dto) {
     * AIDTO updated = aiService.update(botId, dto);
     * return ResponseEntity.ok(updated);
     * }
     */
}