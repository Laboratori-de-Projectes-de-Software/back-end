package com.debateia.adapter.in.rest.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedMessageDTO {
    private Integer botId;
    private Integer matchId;
    private String generatedMessage;
}