package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BotDTO {
    private String name;
    private String urlImagen;
    private String description;
    private String endpoint;
    private int userId;


    public BotDTO (String name, String description, String urlImagen, String endpoint, int userId){
        this.name = name;
        this.description = description;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
        this.userId = userId;
    }
}
