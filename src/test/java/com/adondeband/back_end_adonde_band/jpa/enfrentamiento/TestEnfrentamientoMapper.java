package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnfrentamientoMapper {

    @Autowired
    private EnfrentamientoJpaMapper enfrentamientoJpaMapper;



    // Test no terminado hay que hacer los mapper, este objeto es muy complejo para testearlo ahora
    @Test
    public void comprobarEnfrentamientoEntityToEnfrentamientoSencillo() {
        // Arrange
        EnfrentamientoEntity enfrentamientoEntity = new EnfrentamientoEntity();
        enfrentamientoEntity.setId(1);
        enfrentamientoEntity.setEstado(null);
        enfrentamientoEntity.setResultado(null);
        enfrentamientoEntity.setLocal(null);
        enfrentamientoEntity.setVisitante(null);
        enfrentamientoEntity.setConversacion(null);
        enfrentamientoEntity.setJornada(null);


        // Act
        Enfrentamiento enfrentamiento = enfrentamientoJpaMapper.toDomain(enfrentamientoEntity);

        // Assert
        assertEquals(enfrentamiento.getId(), enfrentamientoEntity.getId());
        assertEquals(enfrentamiento.getEstado(), enfrentamientoEntity.getEstado());
        assertEquals(enfrentamiento.getResultado(), enfrentamientoEntity.getResultado());
        assertEquals(enfrentamiento.getLocal(), enfrentamientoEntity.getLocal());
        assertEquals(enfrentamiento.getVisitante(), enfrentamientoEntity.getVisitante());
        assertEquals(enfrentamiento.getConversacion(), enfrentamientoEntity.getConversacion());
        assertEquals(enfrentamiento.getJornada(), enfrentamientoEntity.getJornada());
    }

}
