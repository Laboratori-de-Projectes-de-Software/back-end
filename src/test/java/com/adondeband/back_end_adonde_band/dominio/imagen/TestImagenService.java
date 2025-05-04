package com.adondeband.back_end_adonde_band.dominio.imagen;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestImagenService {
    @Autowired
    private ImagenService imagenService;

    @Test
    public void guardarImagen() {
        // Arrange
        final String RUTA = "imagen1.jpg";
        Imagen imagen = new Imagen(RUTA);

        // Act
        Imagen imagenSaved = imagenService.guardarImagen(imagen);

        assert imagenSaved != null;
        assertEquals(RUTA, imagenSaved.getRuta());
        assert imagenSaved.getId() != null;
    }

    @Test
    @Transactional
    public void noImagenesDuplicadas() {
        // Arrange
        final String RUTA = "imagen1.jpg";
        Imagen imagen1 = new Imagen(RUTA);
        Imagen imagen2 = new Imagen(RUTA);

        // Act
        // Guardar 2 imágenes con la misma ruta (solo debe guardarse la primera)
        Imagen imagenSaved1 = imagenService.guardarImagen(imagen1);
        Imagen imagenSaved2 = imagenService.guardarImagen(imagen2);

        // Assert
        assert imagenSaved1 != null;
        assert imagenSaved2 != null;
        assertEquals(RUTA, imagenSaved1.getRuta());
        assertEquals(RUTA, imagenSaved2.getRuta());

        // Los ids deben coincidir (solo se habrá guardado 1)
        assertEquals(imagenSaved1.getId(), imagenSaved2.getId());
    }

    @Test
    @Transactional
    public void obtenerImagenPorNombre() {
        // Arrange
        final String RUTA = "imagen1.jpg";
        Imagen imagen = new Imagen(RUTA);
        Imagen imagenSaved = imagenService.guardarImagen(imagen);

        // Act
        List<Imagen> imagenFound = imagenService.obtenerImagenPorRuta(RUTA);

        // Assert
        assert imagenFound != null;
        assertEquals(RUTA, imagenFound.getFirst().getRuta());
        assertEquals(imagenSaved.getId(), imagenFound.getFirst().getId());

    }
}
