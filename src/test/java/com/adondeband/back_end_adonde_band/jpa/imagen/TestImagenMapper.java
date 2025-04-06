package com.adondeband.back_end_adonde_band.jpa.imagen;

import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestImagenMapper {

    @Autowired
    private ImagenJpaMapper imagenJpaMapper;


    @Test
    public void comprobarImagenEntityToImagen() {
        ImagenEntity imagenEntity = new ImagenEntity();
        imagenEntity.setId(1L);
        imagenEntity.setRuta("ruta");

        Imagen imagen = imagenJpaMapper.toDomain(imagenEntity);

        assertEquals(imagenEntity.getId(), imagen.getId());
        assertEquals(imagenEntity.getRuta(), imagen.getRuta());
    }

    @Test
    public void comprobarImagenEntityToImagenWithNullValues() {
        ImagenEntity imagenEntity = new ImagenEntity();
        imagenEntity.setId(0L);
        imagenEntity.setRuta(null);

        Imagen imagen = imagenJpaMapper.toDomain(imagenEntity);

        assertEquals(imagenEntity.getId(), imagen.getId());
        assertEquals(imagenEntity.getRuta(), imagen.getRuta());
    }

    @Test
    public void comprobarImagenEntityToImagenWithEmptyString() {
        ImagenEntity imagenEntity = new ImagenEntity();
        imagenEntity.setId(1L);
        imagenEntity.setRuta("");

        Imagen imagen = imagenJpaMapper.toDomain(imagenEntity);

        assertEquals(imagenEntity.getId(), imagen.getId());
        assertEquals(imagenEntity.getRuta(), imagen.getRuta());
    }
}
