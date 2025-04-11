package com.debateia.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class League {
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private Date date;
    private List<Match> matches;
}
