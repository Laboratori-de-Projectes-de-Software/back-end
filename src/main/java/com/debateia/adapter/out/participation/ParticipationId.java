package com.debateia.adapter.out.participation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationId implements Serializable {
    private Integer leagueId;
    private Integer botId;
}