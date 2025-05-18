package com.debateia.adapter.in.rest.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotMessageDTO {
    private Integer botId;
    private String text;
    private String timestamp;
}
