package com.adondeband.back_end_adonde_band.dominio.rol;

import org.springframework.stereotype.Service;

@Service
public class RolImpl implements RolService{
    private RolPort rolPort;

    @Override
    public Rol crearRol(Rol rol) {
        if (rolPort.findByNombre(rol.getNombre().value()).isEmpty()) {
            return rolPort.save(rol);
        } else {
            throw new IllegalArgumentException("El nombre del rol ya existe");
        }
    }
}
