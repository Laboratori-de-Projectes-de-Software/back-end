package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;

import java.util.List;

public class LeagueResponseDto {
    private int id;
    private String name;
    private String imageUrl;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;
    private LeagueEntity.State state;

    public LeagueResponseDto() {
    }

    public LeagueResponseDto(int id, LeagueEntity.State state, String name, String imageUrl,
            Integer rounds, Long matchTime, List<Integer> bots) {
        this.id = id;
        this.state = state;
        this.name = name;
        this.imageUrl = imageUrl;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LeagueEntity.State getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public Integer getRounds() {
        return rounds;
    }

    public Long getMatchTime() {
        return matchTime;
    }

    public List<Integer> getBots() {
        return bots;
    }

    // Setters
    public void setLeagueId(int id) {
        this.id = id;
    }

    public void setState(LeagueEntity.State state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public void setMatchTime(Long matchTime) {
        this.matchTime = matchTime;
    }

    public void setBots(List<Integer> bots) {
        this.bots = bots;
    }
}
