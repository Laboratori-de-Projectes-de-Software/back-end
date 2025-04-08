package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IBotRepository;
import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.infrastructure.db.dbo.BotDBO;
import jaumesitos.backend.demo.infrastructure.db.mapper.BotDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BotDBORepository implements IBotRepository {
    private final SpringDataBotRepository springDataRepository;
    private final BotDBOMapper mapper;

    @Override
    public Bot save(Bot bot) {
        BotDBO saved = springDataRepository.save(mapper.toDBO(bot));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Bot> findById(String id) {
        //return springDataRepository.findById(id).map(mapper::toBot);
        return null;
    }

    @Override
    public List<Bot> findAll() {
        return springDataRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bot> findByOwnerId(int ownerId) {
        return springDataRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
