package com.adondeband.back_end_adonde_band.API.usuario;

import com.adondeband.back_end_adonde_band.API.bot.BotDTO;
import com.adondeband.back_end_adonde_band.API.imagen.ImagenDTO;
import com.adondeband.back_end_adonde_band.dominio.rol.Rol;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    private String nombre;

    private String correo;

    private String contrasena;

    private ImagenDTO imagen;

    private List<BotDTO> bots;

    private List<Rol> roles;
}
