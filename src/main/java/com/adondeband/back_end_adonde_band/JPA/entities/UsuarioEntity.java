package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class UsuarioEntity {

    @Id
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<BotEntity> getBots() {
        return bots;
    }

    public void setBots(List<BotEntity> bots) {
        this.bots = bots;
    }

    public ImagenEntity getImagen() {
        return imagen;
    }

    public void setImagen(ImagenEntity imagen) {
        this.imagen = imagen;
    }

    public List<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolEntity> roles) {
        this.roles = roles;
    }
}
