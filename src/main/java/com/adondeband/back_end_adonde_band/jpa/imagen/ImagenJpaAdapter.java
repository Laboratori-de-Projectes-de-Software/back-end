package com.adondeband.back_end_adonde_band.jpa.imagen;

import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImagenJpaAdapter implements ImagenPort {
    private final ImagenJpaRepository imagenJpaRepository;
    private final ImagenJpaMapper imagenJpaMapper;

    public ImagenJpaAdapter(final ImagenJpaRepository imagenJpaRepository, final ImagenJpaMapper imagenJpaMapper) {
        this.imagenJpaRepository = imagenJpaRepository;
        this.imagenJpaMapper = imagenJpaMapper;
    }

    @Override
    @Transactional
    public Imagen save(Imagen imagen) {
        return  imagenJpaMapper.toDomain(
                    imagenJpaRepository.save(
                        imagenJpaMapper.toEntity(imagen)));
    }

    @Override
    @Transactional
    public List<Imagen> findByRuta(String ruta) {
        return imagenJpaRepository.findByRuta(ruta)
                .stream()
                .map(imagenJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
