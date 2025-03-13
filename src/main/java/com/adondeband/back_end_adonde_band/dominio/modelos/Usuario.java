package com.adondeband.back_end_adonde_band.dominio.modelos;

import com.adondeband.back_end_adonde_band.dominio.Ids.BotId;
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

    private List<BotId> bots;

    private List<Rol> roles;
}
