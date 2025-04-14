package com.adondeband.back_end_adonde_band.jpa.conversacion;


import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class ConversacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ficheroRuta;

    @OneToOne
    private EnfrentamientoEntity enfrentamiento;
}
