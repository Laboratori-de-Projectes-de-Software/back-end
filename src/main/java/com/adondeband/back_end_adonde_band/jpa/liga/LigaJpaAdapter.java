package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaPort;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaRepository;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaRepository;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigaJpaAdapter implements LigaPort {

    private final LigaJpaRepository ligaJpaRepository;
    private final LigaJpaMapper ligaJpaMapper;
    private final ImagenJpaRepository imagenJpaRepository;
    private final ParticipacionJpaRepository participacionJpaRepository;
    private final BotJpaRepository botJpaRepository;
    private final ParticipacionJpaMapper participacionJpaMapper;

    public LigaJpaAdapter(final LigaJpaRepository ligaJpaRepository, final LigaJpaMapper ligaJpaMapper, ImagenJpaRepository imagenJpaRepository, ParticipacionJpaRepository participacionJpaRepository, BotJpaRepository botJpaRepository, ParticipacionJpaMapper participacionJpaMapper) {
        this.ligaJpaRepository = ligaJpaRepository;
        this.ligaJpaMapper = ligaJpaMapper;
        this.imagenJpaRepository = imagenJpaRepository;
        this.participacionJpaRepository = participacionJpaRepository;
        this.botJpaRepository = botJpaRepository;
        this.participacionJpaMapper = participacionJpaMapper;
    }

    @Override
    @Transactional
    public Liga save(Liga liga) {
        LigaEntity ligaEntity = ligaJpaMapper.toEntity(liga);
        LigaEntity savedLigaEntity = ligaJpaRepository.save(ligaEntity);
        return ligaJpaMapper.toDomain(savedLigaEntity);
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
        return  ligaJpaRepository.findLigaEntitiesByUsuarioId(userId.value())
                .stream()
                .map(ligaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Participacion> findParticipacionesLiga(LigaId ligaId) {
        // Buscar liga por id
        List<LigaEntity> ligasFound = ligaJpaRepository.findById(ligaId.value());

        // Comprobar que la liga existe
        if (ligasFound.isEmpty()) throw new NotFoundException("Este liga no existe");

        // Obtener las participaciones de la liga
        List<ParticipacionEntity> participacionEntity = participacionJpaRepository.findByLiga(ligasFound.getFirst());

        List<Participacion> participaciones = participacionEntity
                .stream()
                .map(participacionJpaMapper::toDomain)
                .toList();

        return participaciones;

//        return  participacionJpaRepository.findByLiga(ligasFound.getFirst())
//                .stream()
//                .map(participacionJpaMapper::toDomain)
//                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Liga actualizarUrlImagen(LigaId ligaId, String url) {

        LigaEntity ligaEntity = ligaJpaRepository.getLigaEntityById(ligaId.value());
        ImagenEntity imagenEntity = imagenJpaRepository.getImagenEntityByRuta(url);

        if (ligaEntity == null) throw new NotFoundException("Esta liga no existe");

        if (imagenEntity == null) {
            // Si la imagen no existe, crear una nueva
            ImagenEntity newImagenEntity = new ImagenEntity();
            newImagenEntity.setRuta(url);
            imagenEntity = imagenJpaRepository.save(newImagenEntity);
        }

        ligaEntity.setImagen(imagenEntity);
        // Guardar la liga actualizada en la base de datos
        LigaEntity savedLigaEntity = ligaJpaRepository.save(ligaEntity);
        return ligaJpaMapper.toDomain(savedLigaEntity);
    }

    @Override
    @Transactional
    public Liga actualizarNRondas(LigaId ligaId, int nrondas) {
        LigaEntity ligaEntity = ligaJpaRepository.getLigaEntityById(ligaId.value());
        if (ligaEntity == null) throw new NotFoundException("Esta liga no existe");
        ligaEntity.setRondas(nrondas);
        LigaEntity savedLigaEntity = ligaJpaRepository.save(ligaEntity);
        return ligaJpaMapper.toDomain(savedLigaEntity);
    }

    @Override
    @Transactional
    public Liga actualizarTiempoRonda(LigaId ligaId, int tiempoRonda) {
        LigaEntity ligaEntity = ligaJpaRepository.getLigaEntityById(ligaId.value());
        if (ligaEntity == null) throw new NotFoundException("Esta liga no existe");
        ligaEntity.setMatchTime(tiempoRonda);
        LigaEntity savedLigaEntity = ligaJpaRepository.save(ligaEntity);
        return ligaJpaMapper.toDomain(savedLigaEntity);
    }

    @Override
    @Transactional
    public Liga actualizarBotsLiga(LigaId ligaId, List<BotId> bots) {
        LigaEntity ligaEntity = ligaJpaRepository.getLigaEntityById(ligaId.value());
        if (ligaEntity == null) throw new NotFoundException("Esta liga no existe");


        List<ParticipacionEntity> actuales = ligaEntity.getParticipaciones();

        participacionJpaRepository.deleteAll(actuales);

        ligaEntity.getParticipaciones().clear();

        for (BotId bot : bots) {
            BotEntity botEntity = botJpaRepository.findById(bot.value()).orElse(null);
            if (botEntity == null) throw new NotFoundException("El bot " + bot.value() + " no existe");

            ParticipacionEntity nuevaParticipacion = new ParticipacionEntity(botEntity, ligaEntity);
            participacionJpaRepository.save(nuevaParticipacion);
            ligaEntity.getParticipaciones().add(nuevaParticipacion);
        }
        return  ligaJpaMapper.toDomain(ligaEntity);
    }

    @Override
    @Transactional
    public Liga addBotToLiga(LigaId ligaId, BotId botId) {
        LigaEntity ligaEntity = ligaJpaRepository.getLigaEntityById(ligaId.value());
        if (ligaEntity == null) throw new NotFoundException("Esta liga no existe");

        BotEntity botEntity = botJpaRepository.findById(botId.value()).orElse(null);
        if (botEntity == null) throw new NotFoundException("El bot " + botId.value() + " no existe");

        ParticipacionEntity nuevaParticipacion = new ParticipacionEntity(botEntity, ligaEntity);
        participacionJpaRepository.save(nuevaParticipacion);
        ligaEntity.getParticipaciones().add(nuevaParticipacion);

        return ligaJpaMapper.toDomain(ligaEntity);
    }
}
