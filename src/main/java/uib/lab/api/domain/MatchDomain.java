package uib.lab.api.domain;

import javax.persistence.*;
import lombok.*;
import uib.lab.api.infraestructure.jpaEntity.Match;

public class MatchDomain {
    private int id;
    private Match.MatchState state;
    private int botId1;
    private int botId2;
    private int roundId;
    private int[] chatsId;

    public MatchDomain(){

    }

    public MatchDomain(int id, Match.MatchState state, int botId1, int botId2, int roundId, int[] chatsId){
        this.id = id;
        this.state = state;
        this.botId1 = botId1;
        this.botId2 = botId2;
        this.roundId = roundId;
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

    public int[] getChatsId() {
        return this.chatsId;
    }

    public void setChatsId(int[] chatsId) {
        this.chatsId = chatsId;
    }
}
