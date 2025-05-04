package com.adondeband.back_end_adonde_band.dominio.imagen;

import java.util.List;

public interface ImagenService {
    Imagen guardarImagen(Imagen imagen);

    List<Imagen> obtenerImagenPorRuta(String ruta);
}
