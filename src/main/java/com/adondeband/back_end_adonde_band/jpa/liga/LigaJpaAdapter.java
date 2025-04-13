package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaPort;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigaJpaAdapter implements LigaPort {

    private final LigaJpaRepository ligaJpaRepository;

    private final LigaJpaMapper ligaJpaMapper;

    public LigaJpaAdapter(final LigaJpaRepository ligaJpaRepository, final LigaJpaMapper ligaJpaMapper) {
        this.ligaJpaRepository = ligaJpaRepository;
        this.ligaJpaMapper = ligaJpaMapper;
    }

    @Override
    @Transactional
    public Liga save(Liga liga) {
        LigaEntity l3 = ligaJpaMapper.toEntity(liga);
        LigaEntity l2 = ligaJpaRepository.save(l3);
        Liga l =  ligaJpaMapper.toDomain(
                l2);
        return l;
    }

    @Override
    @Transactional
    public List<Liga> findById(LigaId id) {
        return  ligaJpaRepository.findById(id.value())
                .stream()
                .map(ligaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Liga> findAll() {
        return  ligaJpaRepository.findAll()
                .stream()
                .map(ligaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Liga> findLigasUsuario(UsuarioId userId) {
        //TODO
        return List.of();
    }
}
