package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;

import java.util.List;

public class LeagueResponseDto {
    private int leagueId;
    private LeagueEntity.State state;
    private String name;
    private String urlImagen;
    private int user; // Owner
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;

    public LeagueResponseDto() {
    }

    public LeagueResponseDto(int leagueId, LeagueEntity.State state, String name, String urlImagen, int user,
            Integer rounds, Long matchTime, List<Integer> bots) {
        this.leagueId = leagueId;
        this.state = state;
        this.name = name;
        this.urlImagen = urlImagen;
        this.user = user;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
    }

    // Getters
    public int getLeagueId() {
        return leagueId;
    }

    public LeagueEntity.State getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public int getUser() {
        return user;
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
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public void setState(LeagueEntity.State state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setUser(int user) {
        this.user = user;
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
