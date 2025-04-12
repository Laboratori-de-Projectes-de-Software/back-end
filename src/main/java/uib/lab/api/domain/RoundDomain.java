package uib.lab.api.domain;

public class RoundDomain {
    private int id;
    private String initialDate;
    private int leagueId;
    private int[] matchesId;

    public RoundDomain(){

    }

    public RoundDomain(int id, String initialDate, int leagueId, int[] matchesId){
        this.id = id;
        this.initialDate = initialDate;
        this.leagueId = leagueId;
        this.matchesId = matchesId;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setInitialDate(String initialDate){
        this.initialDate = initialDate;
    }

    public String getInitialDate(){
        return this.initialDate;
    }

    public void setLeagueId(int leagueId){
        this.leagueId = leagueId;
    }

    public int getLeagueId(){
        return this.leagueId;
    }

    public void setMatchesId(int[] matchesId){
        this.matchesId = matchesId;
    }

    public int[] getMatchesId(){
        return this.matchesId;
    }
    
}

