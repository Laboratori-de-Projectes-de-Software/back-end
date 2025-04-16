package com.example.back_end_eing.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BotSummaryResponseDTO {
    String name;
    Long id;
    String description;
    String urlImage;
}
