package com.debateia.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonTypeName("BotDTO")
public class AIDTO {
    private String name;
    private String description;
    private String urlImagen;
    private String endpoint;
    private int userId;
}