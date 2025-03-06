package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Usuario {

    private String nombre;

    private String correo;

    private String contrasena;

    private Imagen imagen;

    private List<Bot> bots;

    private List<Rol> roles;
}
