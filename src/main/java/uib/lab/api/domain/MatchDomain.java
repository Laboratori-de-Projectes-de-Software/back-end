package uib.lab.api.domain;

import uib.lab.api.infraestructure.jpaEntity.Match;

public class MatchDomain {
    private int id;
    private Match.MatchState state;
    private int result;
    private int botId1;
    private int botId2;
    private int roundId;
    private int rounds;
    private int[] chatsId;

    public MatchDomain(){

    }

    public MatchDomain(int id, Match.MatchState state, int result, int botId1, int botId2, int roundId, int rounds, int[] chatsId){
        this.id = id;
        this.state = state;
        this.result = result;
        this.botId1 = botId1;
        this.botId2 = botId2;
        this.roundId = roundId;
        this.rounds = rounds;
        this.chatsId = chatsId;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setState(Match.MatchState state){
        this.state = state;
    }

    public Match.MatchState getState(){
        return this.state;
    }

    public void setResult(int result){
        this.result = result;
    }

    public int getResult(){
        return this.result;
    }

    
    public void setBotId1(int id){
        this.botId1 = id;
    }

    public int getBotId1(){
        return this.botId1;
    }

    
    public void setBotId2(int id){
        this.botId2 = id;
    }

    public int getBotId2(){
        return this.botId2;
    }

    
    public void setRoundId(int id){
        this.roundId = id;
    }

    public int getRoundId(){
        return this.roundId;
    }

    public void setRounds(int rounds){
        this.rounds = rounds;
    }

    public int getRounds(){
        return this.rounds;
    }

    public int[] getChatsId() {
        return this.chatsId;
    }

    public void setChatsId(int[] chatsId) {
        this.chatsId = chatsId;
    }
}
