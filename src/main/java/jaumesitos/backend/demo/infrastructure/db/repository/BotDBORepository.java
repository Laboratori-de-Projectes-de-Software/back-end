package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IBotRepository;
import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.infrastructure.db.mapper.BotDBOMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Expectation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BotDBORepository implements IBotRepository {
    //private final SpringDataBotRepository springDataRepository;
    private final BotDBOMapper mapper;

    @Override
    public void save(Bot bot) {
        //springDataRepository.save(mapper.toDBO(bot));
    }

    @Override
    public Optional<Bot> findById(String id) {
        //return springDataRepository.findById(id).map(mapper::toBot);
        return null;
    }

    @Override
    public List<Bot> findAll() {
        /*return springDataRepository.findAll().stream()
                .map(mapper::toBot)
                .collect(Collectors.toList());*/
        return null;
    }
}
