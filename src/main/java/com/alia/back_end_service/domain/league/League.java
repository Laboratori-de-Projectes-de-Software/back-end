package com.alia.back_end_service.domain.league;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.round.Round;
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
public class League {

    private Integer id;

    private String name;

    private OffsetDateTime init_time;

    private OffsetDateTime end_time;

    private Integer time_match;

    private Integer number_match;

    private String state;

    private List<Integer> botIds= new ArrayList<>();

    private List<Integer> roundIds = new ArrayList<>();
}
