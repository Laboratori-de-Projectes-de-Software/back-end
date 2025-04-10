package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private String username;
    private String mail;
    private String password;

}
