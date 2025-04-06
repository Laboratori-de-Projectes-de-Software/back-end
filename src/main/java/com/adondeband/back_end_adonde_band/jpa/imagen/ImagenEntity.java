package com.adondeband.back_end_adonde_band.jpa.imagen;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class ImagenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruta;
}
