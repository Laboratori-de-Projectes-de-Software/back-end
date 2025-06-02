package com.debateia.adapter.in.rest.bot;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BotSummaryResponseDTO implements Serializable {
    private String name;
    private int id;
    private String description;
}
