package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaPort;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigaJpaAdapter implements LigaPort {

    private final LigaJpaRepository ligaJpaRepository;

    private final LigaJpaMapper ligaJpaMapper;
    private final UsuarioJpaMapper usuarioJpaMapper;

    public LigaJpaAdapter(final LigaJpaRepository ligaJpaRepository, final LigaJpaMapper ligaJpaMapper, UsuarioJpaMapper usuarioJpaMapper) {
        this.ligaJpaRepository = ligaJpaRepository;
        this.ligaJpaMapper = ligaJpaMapper;
        this.usuarioJpaMapper = usuarioJpaMapper;
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
        // Buscar UsuarioEntity en la base de datos usando el repositorio
        List<LigaEntity> ligasFound = ligaJpaRepository.findByUsuario(
                usuarioJpaMapper.toEntity(userId)
        );

        if (ligasFound.isEmpty()) throw new NotFoundException("Este usuarion no existe");

        // Obtener usuario
        UsuarioEntity usuario = ligasFound.getFirst().getUsuario();

        // Devolver todas las ligas que pertenezcan a este usuario
        return  ligaJpaRepository.findByUsuario(usuario)
                .stream()
                .map(ligaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Liga actualizarUrlImagen(LigaId ligaId, String url) {
        return null;
    }

    @Override
    public Liga actualizarnrondas(LigaId ligaId, int nrondas) {
        return null;
    }

    @Override
    public Liga actualizarTiempoRonda(LigaId ligaId, int tiempoRonda) {
        return null;
    }

    @Override
    public Liga actualizarBots(LigaId ligaId, List<BotId> bots) {
        return null;
    }

    @Override
    public Liga addBotToLiga(LigaId ligaId, BotId botId) {
        return null;
    }
}
