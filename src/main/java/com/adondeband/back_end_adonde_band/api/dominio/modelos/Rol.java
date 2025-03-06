package com.adondeband.back_end_adonde_band.api.dominio.modelos;

import jakarta.persistence.*;

import java.util.List;

public class Rol {
    private String nombre;
    private String descripcion;

    private List<Usuario> usuarios;
}
