package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;


import lombok.*;

@Data
public class ParticipationResponseDto {
    private Long botId;
    private String botName;
    private int points;
    private int position;
    private int nWins;
    private int nDraws;
    private int nLosses;

    public ParticipationResponseDto() {
    }

    public ParticipationResponseDto(Long botId, String botName, int points, int position, int nWins, int nDraws,
            int nLosses) {
        this.botId = botId;
        this.botName = botName;
        this.points = points;
        this.position = position;
        this.nWins = nWins;
        this.nDraws = nDraws;
        this.nLosses = nLosses;
    }

    public Long getBotId() {
        return botId;
    }

    public String getBotName() {
        return botName;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public int getnWins() {
        return nWins;
    }

    public int getnDraws() {
        return nDraws;
    }

    public int getnLosses() {
        return nLosses;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setnWins(int nWins) {
        this.nWins = nWins;
    }

    public void setnDraws(int nDraws) {
        this.nDraws = nDraws;
    }

    public void setnLosses(int nLosses) {
        this.nLosses = nLosses;
    }

}