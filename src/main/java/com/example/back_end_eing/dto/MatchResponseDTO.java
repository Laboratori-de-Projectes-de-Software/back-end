package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchResponseDTO {
    private Integer matchId;
    private String state;
    private String result;
    private String[] fighters;
    private Integer roundNumber;


}
