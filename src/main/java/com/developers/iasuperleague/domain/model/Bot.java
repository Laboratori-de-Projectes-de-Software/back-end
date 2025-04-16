package com.developers.iasuperleague.domain.model;

import java.time.LocalDateTime;

/**
 * Entidad de dominio que representa una Liga.
 */
public class Bot {

    private Integer id;
    private String name;
    private String description;
    private String urlImagen;
    private String endpoint;
    private Integer nWins;
    private Integer nLosses;
    private Integer nDraws;
    private Integer userId;
    private LocalDateTime createdAt;

    public Bot(Integer id, String name, String description, String urlImagen, String endpoint, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getnWins() {
        return nWins;
    }

    public void setnWins(Integer nWins) {
        this.nWins = nWins;
    }

    public Integer getnLosses() {
        return nLosses;
    }

    public void setnLosses(Integer nLosses) {
        this.nLosses = nLosses;
    }

    public Integer getnDraws() {
        return nDraws;
    }

    public void setnDraws(Integer nDraws) {
        this.nDraws = nDraws;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
