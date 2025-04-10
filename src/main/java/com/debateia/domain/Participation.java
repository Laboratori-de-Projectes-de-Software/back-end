package com.debateia.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Participation {
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private Integer aiId;
    private Integer combatId;
}
