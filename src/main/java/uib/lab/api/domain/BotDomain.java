package uib.lab.api.domain;

public class BotDomain {
    private int id;
    private String ideologia;
    private String url;
    private String urlImagen;
    private String description;
    private int userId;
    private int[] matchId1;
    private int[] matchId2;

    public BotDomain(int id, String ideologia, String url, String description, String urlImagen, int userId, int[] matchId1, int[] matchId2) {
        this.id = id;
        this.ideologia = ideologia;
        this.url = url;
        this.description = description;
        this.urlImagen = urlImagen;
        this.userId = userId;
        this.matchId1 = matchId1;
        this.matchId2 = matchId2;
    }

    public BotDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdeologia() {
        return ideologia;
    }

    public void setIdeologia(String ideologia) {
        this.ideologia = ideologia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getMatchId1() {
        return matchId1;
    }

    public void setMatchId1(int[] matchId1) {
        this.matchId1 = matchId1;
    }

    public int[] getMatchId2() {
        return matchId2;
    }

    public void setMatchId2(int[] matchId2) {
        this.matchId2 = matchId2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
