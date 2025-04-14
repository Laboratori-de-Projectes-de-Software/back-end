package jaumesitos.backend.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class Participation {
    int leagueid;
    int botid;
    int points;
    int matches;
    int wins;
    int draws;
    int losses;
    LocalDateTime inscription;
}
