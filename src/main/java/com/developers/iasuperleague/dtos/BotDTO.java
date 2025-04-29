package com.developers.iasuperleague.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BotDTO {
    private Integer id;
    private String name;
    private String description;
    private String urlImagen;
    private String endpoint;
}
