package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Bot")
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    @Column(nullable = false, length = 100)
    private String name;

    // prompt
    @Column(nullable = false, length = 255)
    private String quality;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    // url de datos
    @Column(nullable = false, length = 255)
    private String apiUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private UserEntity usuario; // Relación con Usuario

    @ManyToMany(mappedBy = "bots")
    private Set<LeagueEntity> ligas = new HashSet<>();

    public BotEntity() {
    }

    public BotEntity(Long id, String name, String quality, String imageUrl, String apiUrl) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.imageUrl = imageUrl;
        this.apiUrl = apiUrl;
    }

    public BotEntity(Long id, String name, String quality, String imageUrl, String apiUrl, UserEntity usuario) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.imageUrl = imageUrl;
        this.apiUrl = apiUrl;
        this.usuario = usuario;
    }

    // Métodos getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuality() {
        return quality;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public Set<LeagueEntity> getLigas() {
        return ligas;
    }

    // Métodos setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public void setLigas(Set<LeagueEntity> ligas) {
        this.ligas = ligas;
    }
}
