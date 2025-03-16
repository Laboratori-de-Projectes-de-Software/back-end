package com.adondeband.back_end_adonde_band.dominio.usuario;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.rol.RolId;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Usuario {

    private UsuarioId nombre;

    private String correo;

    private String contrasena;

    private Imagen imagen;

    private List<BotId> bots;

    private List<RolId> roles;
}
