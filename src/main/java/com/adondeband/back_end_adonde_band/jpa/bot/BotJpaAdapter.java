package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotPort;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaRepository;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;
    private final BotJpaMapper botJpaMapper;
    private final UsuarioJpaMapper usuarioJpaMapper;
    private final ImagenJpaRepository imagenJpaRepository;

    public BotJpaAdapter(
            final BotJpaRepository botJpaRepository,
            final BotJpaMapper botJpaMapper,
            final UsuarioJpaMapper usuarioJpaMapper,
            final ImagenJpaRepository imagenJpaRepository) {
        this.botJpaRepository = botJpaRepository;
        this.botJpaMapper = botJpaMapper;
        this.usuarioJpaMapper = usuarioJpaMapper;
        this.imagenJpaRepository = imagenJpaRepository;
    }

    public Bot actualizarUrlImagen(BotId botId, String url) {
        // Actualizar la URL de la imagen en el bot

        ImagenEntity imagenEntity = imagenJpaRepository.getImagenEntityByRuta(url);
        BotEntity botEntity = botJpaRepository.getBotEntityByNombre(botId.value());

        if (botEntity == null) throw new NotFoundException("Este bot no existe");


        if (imagenEntity == null) {
            // Si la imagen no existe, crear una nueva
            ImagenEntity newImagenEntity = new ImagenEntity();
            newImagenEntity.setRuta(url);
            imagenEntity = imagenJpaRepository.save(newImagenEntity);
        }
        botEntity.setImagen(imagenEntity);

        // Guardar el bot actualizado en la base de datos
        return botJpaMapper.toDomain(botJpaRepository.save(botEntity));
    }

    @Override
    public Bot actualizarDescripcion(BotId botId, String descripcion) {
        return null;
    }

    @Override
    public Bot actualizarEndpoint(BotId botId, String endpoint) {
        return null;
    }

    @Override
    @Transactional
    public Bot save(Bot bot) {
        return botJpaMapper.toDomain(
                botJpaRepository.save(
                        botJpaMapper.toEntity(bot)));
    }

    @Override
    @Transactional
    public List<Bot> findByNombre(String s) {
        return botJpaRepository.findByNombre(s)
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Bot> findAll() {
        return botJpaRepository.findAll()
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Bot> findBotsUsuario(UsuarioId userId) {
        // Buscar UsuarioEntity en la base de datos usando el repositorio
        List<BotEntity> botsFound =  botJpaRepository.findByUsuario(
                usuarioJpaMapper.toEntity(userId)
        );

        if (botsFound.isEmpty()) throw new NotFoundException("Este usuario no existe");

        UsuarioEntity usuarioEntity = botsFound.getFirst().getUsuario();

        // Mapear los bots asociados al usuario
        return botJpaRepository.findByUsuario(usuarioEntity)
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
