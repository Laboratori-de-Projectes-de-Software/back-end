package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotMessageResponse {

    private String reply;
    private Integer botId;
    private LocalDateTime timestamp;
}
