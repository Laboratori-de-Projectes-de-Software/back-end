package com.adondeband.back_end_adonde_band.dominio.imagen;

import java.util.List;

public interface ImagenPort {
    Imagen save(Imagen imagen);

    List<Imagen> findByRuta(String ruta);
}
