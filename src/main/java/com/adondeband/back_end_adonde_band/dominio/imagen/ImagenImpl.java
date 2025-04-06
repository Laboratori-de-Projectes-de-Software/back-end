package com.adondeband.back_end_adonde_band.dominio.imagen;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenImpl implements ImagenService {

    private final ImagenPort imagenPort;

    public ImagenImpl(ImagenPort imagenPort) {
        this.imagenPort = imagenPort;
    }

    @Override
    public Imagen guardarImagen(Imagen imagen) {
        // Buscar imagen por ruta
        List <Imagen> imgFound = obtenerImagenPorRuta(imagen.getRuta());

        if (imgFound.isEmpty()) {
            // Si no se encontró imagen, debemos guardarla
            return imagenPort.save(imagen);
        } else {
            // Si se encontró imagen, la devolvemos
            return imgFound.getFirst();
        }
    }

    @Override
    public List<Imagen> obtenerImagenPorRuta(String ruta) {
        return imagenPort.findByRuta(ruta);
    }
}
