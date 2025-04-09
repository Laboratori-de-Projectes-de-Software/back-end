package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonTypeName("BotSummaryResponseDTO")
public class AISummaryResponseDTO {
    private String nombre;
    private int id;
    private String description;
}
