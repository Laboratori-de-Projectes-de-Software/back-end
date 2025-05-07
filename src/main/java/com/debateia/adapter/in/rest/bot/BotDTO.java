package com.debateia.adapter.in.rest.bot;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("BotDTO")
public class BotDTO implements Serializable {
    private String name;
    private String description;
    private String urlImagen;
    private String endpoint;
}