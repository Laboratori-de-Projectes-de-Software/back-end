package com.debateia.adapter.in.rest.bot;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("BotDTO")
public class CreateBotDTO implements Serializable {
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;
}