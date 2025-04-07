package com.adondeband.back_end_adonde_band.API.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BotDTOMin {

    private String nombre;

    private String cualidad;

    private String imagen; // luego quizá sea ImagenDTO

    private String endpoint;

    public BotDTOMin(BotDTO botDTO) {
        this.nombre = botDTO.getNombre();
        this.cualidad = botDTO.getCualidad();
        this.imagen = botDTO.getImagen();
        this.endpoint = botDTO.getEndpoint();
    }
}
