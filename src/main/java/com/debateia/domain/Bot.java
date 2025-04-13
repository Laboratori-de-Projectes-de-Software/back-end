package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {
    private Integer id;
    private String name;
    private String description;
    private String endpoint;
    private String urlImage;
    private int wins;
    private int draws;
    private int losses;
    private Integer userId;
}


