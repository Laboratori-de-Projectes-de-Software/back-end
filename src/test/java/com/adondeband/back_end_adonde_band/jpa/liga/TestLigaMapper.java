package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLigaMapper {

    @Autowired
    private LigaJpaMapper ligaJpaMapper;

    @Test
    public void comprobarLigaEntityToLigaSencillo() {
        // Arrange
        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setId(1L);
        ligaEntity.setNombre("Liga 1");
        ligaEntity.setFechaInicio(LocalDateTime.now());
        ligaEntity.setFechaFin(LocalDateTime.now());


        // Act
        Liga liga = ligaJpaMapper.toDomain(ligaEntity);

        // Assert
        assertNotNull("Liga ID should not be null", liga.getId());
        assertEquals(liga.getId().value(), ligaEntity.getId().longValue());
        assertEquals(liga.getNombre(), ligaEntity.getNombre());
        assertEquals(liga.getFechaInicio(), ligaEntity.getFechaInicio());
        assertEquals(liga.getFechaFin(), ligaEntity.getFechaFin());
    }

    @Test
    public void comprobarLigaToLigaEntitySencillo() {
        // Arrange
        Liga liga = new Liga();
        liga.setId(new LigaId(1));
        liga.setNombre("Liga 1");
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setEstado(ESTADO.PENDING);
        liga.setMatchTime(120L);
        liga.setImagen(null);
        liga.setUsuario(null);
        liga.setParticipaciones(null);

        // Act
        LigaEntity ligaEntity = ligaJpaMapper.toEntity(liga);

        // Assert
        assertNotNull("Liga Nombre should not be null", ligaEntity.getNombre());
        assertEquals(liga.getId().value(), ligaEntity.getId().longValue());
        assertEquals(liga.getNombre(), ligaEntity.getNombre());
        assertEquals(liga.getFechaInicio(), ligaEntity.getFechaInicio());
        assertEquals(liga.getFechaFin(), ligaEntity.getFechaFin());
    }

    @Test
    public void comprobarMapLigaid() {

        LigaId ligaId = new LigaId(1);

        long id = ligaJpaMapper.map(ligaId);


        assertEquals(id, ligaId.value());

    }

}
