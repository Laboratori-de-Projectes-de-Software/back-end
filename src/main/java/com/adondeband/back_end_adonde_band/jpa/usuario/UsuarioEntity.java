package com.adondeband.back_end_adonde_band.jpa.usuario;

import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.rol.RolEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<BotEntity> bots;

    @ManyToOne
    private ImagenEntity imagen;

    @ManyToMany
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<RolEntity> roles;
    @OneToMany
    private List<LigaEntity> ligas;
}
