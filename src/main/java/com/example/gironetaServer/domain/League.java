package com.example.gironetaServer.domain;

import java.util.List;

public class League {
    private Long id;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Long> bots;
    private Long userId;

    public League() {
    }

    public League(Long id, String name, String urlImagen, Integer rounds, Long matchTime, List<Long> bots,
            Long userId) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
        this.userId = userId;
    }

    // Métodos getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public Integer getRounds() {
        return rounds;
    }

    public Long getMatchTime() {
        return matchTime;
    }

    public List<Long> getBots() {
        return bots;
    }

    public Long getUserId() {
        return userId;
    }

    // Métodos setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public void setMatchTime(Long matchTime) {
        this.matchTime = matchTime;
    }

    public void setBots(List<Long> bots) {
        this.bots = bots;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}