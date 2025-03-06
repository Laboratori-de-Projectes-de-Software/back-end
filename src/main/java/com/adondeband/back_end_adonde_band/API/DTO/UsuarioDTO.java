package com.adondeband.back_end_adonde_band.API.DTO;

import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import com.adondeband.back_end_adonde_band.dominio.modelos.Imagen;
import com.adondeband.back_end_adonde_band.dominio.modelos.Rol;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    private String nombre;

    private String correo;

    private String contrasena;

    private Imagen imagen;

    private List<BotDTO> bots;

    private List<Rol> roles;
}
