package com.adondeband.back_end_adonde_band.jpa.rol;

import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class RolEntity {

    @Id
    @Column(nullable = false, unique = true, length = 36)
    private String nombre;
    private String descripcion;

    @ManyToMany
    private List<UsuarioEntity> usuarios;
}
