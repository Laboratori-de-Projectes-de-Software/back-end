package jaumesitos.backend.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
