package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationResponseDTO {
    private Integer botId;
    private String name;
    private Integer points;
    private Integer position;
}
