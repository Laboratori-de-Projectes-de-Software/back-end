package com.debateia.adapter.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueBotsId implements Serializable {
    private Integer leagueId;
    private Integer botId;
}