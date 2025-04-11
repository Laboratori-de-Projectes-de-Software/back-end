package com.adondeband.back_end_adonde_band.dominio.usuario;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.rol.RolId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Usuario {

    private UsuarioId id;

    private String nombre;

    private String correo;

    private String contrasena;

    private Imagen imagen;

    private List<BotId> bots;

    private List<RolId> roles;
}
