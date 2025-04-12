package uib.lab.api.domain;

public class ChatDomain {

    private int id;
    private String text;
    private String time;
    private int botId;
    private int matchId;

    public ChatDomain(){

    }

    public ChatDomain(int id, String text, String time, int matchId, int botId){
        this.id = id;
        this.text = text;
        this.time = time;
        this.botId = botId;
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBotId() {
        return this.botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public int getMatchId() {
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

}
