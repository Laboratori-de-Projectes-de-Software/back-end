package com.example.demo.domain.model;

import java.time.LocalDateTime;

/**
 * Entidad de dominio que representa una Liga.
 */
public class League {

    private Integer id;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private Long[] bots;
    private LocalDateTime createdAt;

    public League(Integer id, String name, String urlImagen, Integer rounds, Long matchTime, Long[] bots) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public Long getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Long matchTime) {
        this.matchTime = matchTime;
    }

    public Long[] getBots() {
        return bots;
    }

    public void setBots(Long[] bots) {
        this.bots = bots;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
