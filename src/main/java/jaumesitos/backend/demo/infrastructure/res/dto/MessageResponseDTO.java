package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponseDTO {
    Integer matchId;
    String state;
    Integer result;
    String [] fighters;
    Integer roundNumber;
}
