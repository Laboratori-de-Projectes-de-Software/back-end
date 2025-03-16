package com.adondeband.back_end_adonde_band.jpa.rol;

import com.adondeband.back_end_adonde_band.dominio.rol.Rol;
import com.adondeband.back_end_adonde_band.dominio.rol.RolPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RolJpaAdapter implements RolPort {

    private final RolJpaRepository rolJpaRepository;

    private final RolJpaMapper rolJpaMapper;

    public RolJpaAdapter(final RolJpaRepository rolJpaRepository, final RolJpaMapper rolJpaMapper) {
        this.rolJpaRepository = rolJpaRepository;
        this.rolJpaMapper = rolJpaMapper;
    }

    @Override
    public Rol save(Rol rol) {
        return rolJpaMapper.toDomain(
                rolJpaRepository.save(
                        rolJpaMapper.toEntity(rol)));
    }

    @Override
    public List<Rol> findByNombre(String s) {
        return rolJpaRepository.findByNombre(s)
                .stream()
                .map(rolJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
