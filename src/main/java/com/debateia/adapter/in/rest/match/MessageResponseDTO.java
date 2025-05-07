package com.debateia.adapter.in.rest.match;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class MessageResponseDTO implements Serializable {
    private String text;
    private int botId;
    private String time;
}
