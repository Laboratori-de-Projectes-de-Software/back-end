package org.example.backend.databaseapi.jpa.bot;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.exception.MetodoNoPermitido;
import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
import org.example.backend.databaseapi.jpa.liga.LigaJpaAdapter;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class BotJpaAdapter implements CreateBotPort, FindBotPort, UpdateBotPort, DeleteBotPort, FindAllUserBots, FindAllBotsPort {

    private final BotJpaRepository botJpaRepository;
    @Lazy
    private final UsuarioJpaAdapter usuarioJpaAdapter;
    @Lazy
    private final LigaJpaAdapter ligaJpaAdapter;
    private final BotJpaMapper botJpaMapper;

    @Override
    @Transactional
    public Optional<Bot> createBot(Bot bot) {
        if(!botJpaRepository.existsByNombreOrUrl(bot.getNombre(),bot.getUrl())){
            BotJpaEntity newbot=BotJpaEntity.builder()
                    .url(bot.getUrl())
                    .prompt(bot.getPrompt())
                    .cualidad(bot.getCualidad())
                    .imagen(bot.getImagen())
                    .usuario(
                            usuarioJpaAdapter.getUser(bot.getUsuario().value())
                                    .orElseThrow()
                    )
                    .nombre(bot.getNombre())
                    .build();
            return Optional.of(botJpaMapper.toDomain(botJpaRepository.save(newbot)));
        }
        return Optional.empty();
    }

    @Override
    public void deleteBot(int id_bot) {
        botJpaRepository.deleteById(id_bot);
    }

    @Override
    public Optional<Bot> findBot(int id_bot) {
        return botJpaRepository.findById(id_bot).map(botJpaMapper::toDomain);
    }

    @Override
    @Transactional
    public Bot updateBot(Bot changedBot, Integer id) {
        //Tal vez se tenga que comprobar si la url(IA API ENDPOINT) ya existe, eso depende de los requisitos,
        //actualmente podria estar en 2 bots diferentes al no estar especificado
        Integer botOwner=botJpaRepository.findById(id)
                .orElseThrow()
                .getUsuario()
                .getUserId();
        if(!botOwner.equals(changedBot.getUsuario().value())){
            throw new MetodoNoPermitido("No puedes editar un bot del que no eres dueño");
        }
        BotJpaEntity newbot=BotJpaEntity.builder()
                .url(changedBot.getUrl())
                .prompt(changedBot.getPrompt())
                .cualidad(changedBot.getCualidad())
                .imagen(changedBot.getImagen())
                .usuario(
                        usuarioJpaAdapter.getUser(changedBot.getUsuario().value())
                                .orElseThrow()
                )
                .nombre(changedBot.getNombre())
                .idBot(id)
                .build();

        return botJpaMapper.toDomain(botJpaRepository.save(newbot));
    }

    @Override
    @Transactional
    public List<Bot> findAllBots(int id_user) {
        return botJpaRepository.findByUsuario_UserId(id_user)
                .stream()
                .map(botJpaMapper::toDomain)
                .toList();

    }

    @Override
    public List<Bot> findAllBots() {
        return botJpaRepository.findAll()
                .stream()
                .map(botJpaMapper::toDomain)
                .toList();

    }

    @Override
    public List<Bot> findAllBotsFilter(BotsFilterRequest request){
        return botJpaRepository.findAll()
                .stream()
                .filter(bot -> request.getCualidad() == null || request.getCualidad().contains(bot.getCualidad()) || bot.getCualidad().contains(request.getCualidad()))
                .filter(bot -> request.getNombre() == null || request.getNombre().contains(bot.getNombre()) || bot.getNombre().contains(request.getNombre()))
                .filter(bot -> request.getUsuario() == null || request.getUsuario().contains(bot.getUsuario().getNombre()) || bot.getUsuario().getNombre().contains(request.getUsuario()))
                .map(botJpaMapper::toDomain)
                .sorted(getComparator(request.getOrden()))
                .toList();
    }

    private Comparator<Bot> getComparator(Integer orden) {
        if (orden == null || orden == 0)
            return (o1, o2) -> 0; // Orden por defecto

        return switch (orden) {
            case 1 -> Comparator.comparing(Bot::getNombre);
            case 2 -> Comparator.comparing(Bot::getNombre).reversed();
            case 3 -> Comparator.comparing(Bot::getCualidad);
            default -> Comparator.comparing(Bot::getCualidad).reversed();
        };
    }

    public Optional<BotJpaEntity> getJpaBot(int id_bot){
        return botJpaRepository.findById(id_bot);
    }
}
