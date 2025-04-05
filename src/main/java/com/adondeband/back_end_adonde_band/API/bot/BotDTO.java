package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotDTO {

    private String nombre;

    private String cualidad;

    private String imagen; // luego quizá sea ImagenDTO

    private String endpoint;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    public BotDTO(BotDTOMin botDTOMin) {
        this.nombre = botDTOMin.getNombre();
        this.cualidad = botDTOMin.getCualidad();
        this.imagen = botDTOMin.getImagen();
        this.endpoint = botDTOMin.getEndpoint();

        // default de otros atributos
        numDerrotas = numEmpates = numVictorias = 0;
    }
}
