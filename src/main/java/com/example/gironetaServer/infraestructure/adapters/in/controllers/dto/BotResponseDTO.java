package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BotResponseDTO {
    private Long id;
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;
    private int nWins;
    private int nLosses;
    private int nDraws;

    public BotResponseDTO() {
    }

    public BotResponseDTO(Long id, String name, String quality, String imageUrl, String apiUrl, int nWins, int nLosses,
            int nDraws) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.imageUrl = imageUrl;
        this.apiUrl = apiUrl;
        this.nWins = nWins;
        this.nLosses = nLosses;
        this.nDraws = nDraws;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuality() {
        return quality;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public int getnWins() {
        return nWins;
    }

    public int getnLosses() {
        return nLosses;
    }

    public int getnDraws() {
        return nDraws;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setnWins(int nWins) {
        this.nWins = nWins;
    }

    public void setnLosses(int nLosses) {
        this.nLosses = nLosses;
    }

    public void setnDraws(int nDraws) {
        this.nDraws = nDraws;
    }
}
