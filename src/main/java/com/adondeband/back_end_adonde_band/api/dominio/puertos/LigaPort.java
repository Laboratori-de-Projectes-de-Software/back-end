package com.adondeband.back_end_adonde_band.api.dominio.puertos;

import com.adondeband.back_end_adonde_band.api.dominio.modelos.Liga;
import java.util.List;

public interface LigaPort {
    Liga save(Liga liga);

    List<Liga> findByNombre(String s);
}
