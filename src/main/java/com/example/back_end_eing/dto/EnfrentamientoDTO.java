package com.example.back_end_eing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class EnfrentamientoDTO {
    private int matchId;
    private String state;
    private int result;
    private List<BotDTO> fighters;
    private String roundNumbers;
}
