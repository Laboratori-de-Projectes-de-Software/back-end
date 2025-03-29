package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLigaService {

    @Autowired
    private LigaService ligaService;

    @Test
    @Transactional
    public void testSaveLiga() {
        // Arrange
        Liga liga = new Liga();
        liga.setNombre("La liga EA Sports");
        liga.setEstado(ESTADO.PENDIENTE);
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setImagen(null);
        liga.setParticipaciones(new ArrayList<>());

        // Act
        Liga ligaSaved = ligaService.crearLiga(liga);

        // Assert
        assertNull(liga.getId());
        assertNotNull("El id de la liga no debe ser null", ligaSaved.getId());
        assertEquals(liga.getNombre(), ligaSaved.getNombre());
    }
}
