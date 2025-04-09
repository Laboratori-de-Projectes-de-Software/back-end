package com.alia.back_end_service.domain.round;

import com.alia.back_end_service.domain.league.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Round {

    private Integer id;

    private Integer number_round;

    private OffsetDateTime init_time;

    private OffsetDateTime end_time;

    private String state;

    private Integer leagueId;

    private List<Integer> gameIds = new ArrayList<>();

    private List<Integer> classificationIds = new ArrayList<>();

}
