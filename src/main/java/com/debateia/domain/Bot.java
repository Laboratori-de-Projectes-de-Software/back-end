package com.debateia.domain;

import com.debateia.adapter.out.persistence.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bot {
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private String trait;
    private String secret;
    private String endpoint;
    private Boolean enabled = true;
    private UserEntity user;
}
