package com.example.gironetaServer.domain;

public class Bot {
    private Long id; //El consenso usa INT no LONG
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;
    private Long usuario_id;
    private int nWins;
    private int nLosses;
    private int nDraws;

    public Bot() {
    }

    public Bot(Long id, String name, String quality, String imageUrl, String apiUrl, Long usuario_id, int nWins, int nLosses, int nDraws) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.imageUrl = imageUrl;
        this.apiUrl = apiUrl;
        this.usuario_id = usuario_id;
        this.nWins = nWins;
        this.nLosses = nLosses;
        this.nDraws = nDraws;
    }

    // Métodos getter
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

    public Long getUsuario_id() {
        return usuario_id;
    }

    // Métodos setter
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

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getnWins() {
        return nWins;
    }

    public void setnWins(int nWins) {
        this.nWins = nWins;
    }

    public int getnLosses() {
        return nLosses;
    }

    public void setnLosses(int nLosses) {
        this.nLosses = nLosses;
    }

    public int getnDraws() {
        return nDraws;
    }

    public void setnDraws(int nDraws) {
        this.nDraws = nDraws;
    }
}
