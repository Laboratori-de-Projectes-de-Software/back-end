package com.debateia.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MessageResponseDTO {
    private String text;
    private int botId;
    private String time;
}
