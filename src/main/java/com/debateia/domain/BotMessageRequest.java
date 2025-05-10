package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotMessageRequest {
    private String prompt;
    private Integer matchId;
}
