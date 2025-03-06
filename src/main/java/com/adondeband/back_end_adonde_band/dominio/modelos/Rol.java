package com.adondeband.back_end_adonde_band.dominio.modelos;

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
