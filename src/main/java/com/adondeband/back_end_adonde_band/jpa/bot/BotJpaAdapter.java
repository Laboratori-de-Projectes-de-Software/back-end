package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotPort;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaRepository;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;
    private final ImagenJpaRepository imagenJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final BotJpaMapper botJpaMapper;
    private final UsuarioJpaMapper usuarioJpaMapper;


    public BotJpaAdapter(
            final BotJpaRepository botJpaRepository,
            final BotJpaMapper botJpaMapper,
            final UsuarioJpaMapper usuarioJpaMapper,
            final ImagenJpaRepository imagenJpaRepository,
            UsuarioJpaRepository usuarioJpaRepository) {
        this.botJpaRepository = botJpaRepository;
        this.botJpaMapper = botJpaMapper;
        this.usuarioJpaMapper = usuarioJpaMapper;
        this.imagenJpaRepository = imagenJpaRepository;
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    @Transactional
    public Bot actualizarUrlImagen(BotId botId, String url) {
        // Actualizar la URL de la imagen en el bot

        ImagenEntity imagenEntity = imagenJpaRepository.getImagenEntityByRuta(url);
        BotEntity botEntity = botJpaRepository.findById(botId.value()).orElse(null);
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
    @Transactional
    public Bot actualizarDescripcion(BotId botId, String descripcion) {
        BotEntity botEntity = botJpaRepository.findById(botId.value()).orElse(null);
        if (botEntity == null) throw new NotFoundException("Este bot no existe");
        botEntity.setCualidad(descripcion);

        // Guardar el bot actualizado en la base de datos
        return botJpaMapper.toDomain(botJpaRepository.save(botEntity));
    }

    @Override
    @Transactional
    public Bot actualizarEndpoint(BotId botId, String endpoint) {
        BotEntity botEntity = botJpaRepository.findById(botId.value()).orElse(null);
        if (botEntity == null) throw new NotFoundException("Este bot no existe");
        botEntity.setEndpoint(endpoint);

        // Guardar el bot actualizado en la base de datos
        return botJpaMapper.toDomain(botJpaRepository.save(botEntity));
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
    public Bot findByNombre(String s) {
        BotEntity botEntity = botJpaRepository.findByNombre(s);
        return botJpaMapper.toDomain(botEntity);
    }

    @Override
    @Transactional
    public Bot findById(Long id) {
        BotEntity botEntity = botJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Este bot no existe"));
        return botJpaMapper.toDomain(botEntity);
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
        List<UsuarioEntity> usuariosFound = usuarioJpaRepository.findById(userId.value());

        if (usuariosFound.isEmpty()) throw new NotFoundException("Este usuario no existe");

        // Mapear los bots asociados al usuario
        return botJpaRepository.findByUsuario(usuariosFound.getFirst())
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
