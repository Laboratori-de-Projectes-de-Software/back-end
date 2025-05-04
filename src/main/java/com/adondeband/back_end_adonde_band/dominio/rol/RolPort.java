package com.adondeband.back_end_adonde_band.dominio.rol;

import java.util.List;

public interface RolPort {
    Rol save(Rol rol);

    List<Rol> findByNombre(String s);
}
