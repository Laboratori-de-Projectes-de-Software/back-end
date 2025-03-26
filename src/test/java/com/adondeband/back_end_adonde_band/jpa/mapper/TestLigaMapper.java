package com.adondeband.back_end_adonde_band.jpa.mapper;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestLigaMapper {

    private LigaJpaMapper ligaJpaMapper;

    @Before
    public void setUp() {
        // Obtener la implementación generada por MapStruct
        ligaJpaMapper = Mappers.getMapper(LigaJpaMapper.class);
    }

    @Test
    public void comprobarLigaEntityToLigaSencillo() {
        // Arrange
        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setId(1);
        ligaEntity.setNombre("Liga 1");
        ligaEntity.setFechaInicio(LocalDateTime.now());
        ligaEntity.setFechaFin(LocalDateTime.now());



        // Act
        Liga liga = ligaJpaMapper.toDomain(ligaEntity);

        // Assert
        assertNotNull("Liga ID should not be null", liga.getId());
        assertEquals(liga.getId().value(), ligaEntity.getId());
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
