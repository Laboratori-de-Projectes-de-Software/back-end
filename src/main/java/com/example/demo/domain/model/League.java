package com.example.demo.domain.model;

import java.time.LocalDateTime;

/**
 * Entidad de dominio que representa una Liga.
 */
public class League {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public League() {
    }

    public League(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Método de negocio para actualizar el nombre de la liga.
    public void updateLeagueName(String newName) {
        this.name = newName;
    }
}
