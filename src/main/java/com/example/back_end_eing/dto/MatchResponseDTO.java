package com.example.back_end_eing.dto;

import lombok.*;

@Builder
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
