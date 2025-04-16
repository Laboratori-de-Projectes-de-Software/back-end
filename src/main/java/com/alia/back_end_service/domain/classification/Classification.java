package com.alia.back_end_service.domain.classification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classification {

    private Integer id;

    private Integer points;

    private Integer number_matchs;

    private Integer win_matchs;

    private Integer tie_matchs;

    private Integer lose_matchs;

    private Integer botId;

    private Integer leagueId;
}
