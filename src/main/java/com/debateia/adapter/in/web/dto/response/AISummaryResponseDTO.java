package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("BotSummaryResponseDTO")
public class AISummaryResponseDTO implements Serializable {
    private String nombre;
    private int id;
    private String description;
}
