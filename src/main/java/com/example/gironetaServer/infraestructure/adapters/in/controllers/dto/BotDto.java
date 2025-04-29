package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class BotDto {
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;

    public BotDto() {
    }

    public BotDto(String name, String quality, String imageUrl, String apiUrl) {
        this.name = name;
        this.quality = quality;
        this.imageUrl = imageUrl;
        this.apiUrl = apiUrl;
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
}
