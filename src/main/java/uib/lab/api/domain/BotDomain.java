package uib.lab.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BotDomain {
    private int id;
    private String name;
    private String ideologia;
    private String url;
    private String urlImagen;
    private String description;
    private int nWins;
    private int nLosses;
    private int nDraws;
    private int userId;
    private int[] matchId1;
    private int[] matchId2;
    private int[] chats;

    public BotDomain() {

    }
}
