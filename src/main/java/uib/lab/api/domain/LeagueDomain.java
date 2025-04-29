package uib.lab.api.domain;

import uib.lab.api.infraestructure.jpaEntity.League;

public class LeagueDomain {

    private int id;
    private String name;
    private String urlImagen;
    private int playTime;
    private int numRounds;
    private League.LeagueState state;
    private int userId;
    private int[] botIds;

    public LeagueDomain() {
    }

    public LeagueDomain(int id, String name, String urlImagen, int playTime, int numRounds, League.LeagueState state, int userId, int[] botIds) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.playTime = playTime;
        this.numRounds = numRounds;
        this.state = state;
        this.userId = userId;
        this.botIds = botIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    public League.LeagueState getState() {
        return state;
    }

    public void setState(League.LeagueState state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getBotIds() {
        return botIds;
    }

    public void setBotIds(int[] botIds) {
        this.botIds = botIds;
    }
}
