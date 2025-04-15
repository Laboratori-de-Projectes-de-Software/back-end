package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BotResponseDTO {
    private Long botId;
    private String name;
    private String description;
    private String urlImage;
    private int nWins;
    private int nLosses;
    private int nDraws;

    public BotResponseDTO() {
    }

    public BotResponseDTO(Long botId, String name, String description, String urlImage, int nWins, int nLosses,
            int nDraws) {
        this.botId = botId;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.nWins = nWins;
        this.nLosses = nLosses;
        this.nDraws = nDraws;
    }

    public Long getBotId() {
        return botId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
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

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
