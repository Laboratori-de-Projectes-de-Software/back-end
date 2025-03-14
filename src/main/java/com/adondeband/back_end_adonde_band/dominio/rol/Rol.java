package com.adondeband.back_end_adonde_band.dominio.rol;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Rol {
    private String nombre;
    private String descripcion;

    private List<Usuario> usuarios;
}
