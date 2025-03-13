package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class ImagenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ruta;
}
