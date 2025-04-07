package com.example.demo.dtos;

import java.time.LocalDateTime;

/**
 * Data Transfer Object para la información de una liga.
 */
public class LeagueDTO {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public LeagueDTO() {
    }

    public LeagueDTO(Long id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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
}
