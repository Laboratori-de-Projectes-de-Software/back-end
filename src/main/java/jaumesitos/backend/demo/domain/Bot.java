package jaumesitos.backend.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot {
    private int id;
    private String name;
    private String description;
    private String urlImage;
    private String endpoint;
    private int wins;
    private int losses;
    private int draws;
    private int ownerId;

    public Bot(int id, String description, String name, String urlImage, String endpoint, int wins, int losses, int draws, int ownerId) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.urlImage = urlImage;
        this.endpoint = endpoint;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.ownerId = ownerId;
    }
}
