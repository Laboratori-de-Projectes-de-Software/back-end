package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import java.util.List;

public class LeagueDto {
    private String name;
    private String imageUrl;
    private Integer rounds;
    private Long matchTime;
    private String user; // Owner

    public LeagueDto() {
    }

    public LeagueDto(String name, String imageUrl, Integer rounds, Long matchTime,
                     String user) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.user = user;
    }

    // Métodos getter
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

    public String getUser() {
        return user;
    }

    // Métodos setter
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

    public void setUser(String user) {
        this.user = user;
    }
}
