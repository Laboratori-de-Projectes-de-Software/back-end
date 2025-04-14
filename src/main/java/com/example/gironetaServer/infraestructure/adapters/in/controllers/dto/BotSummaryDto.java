package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BotSummaryDto {
    private Long id; //El consenso usa INT no LONG
    private String name;
    private String description;

    public BotSummaryDto(){

    }

    public BotSummaryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
