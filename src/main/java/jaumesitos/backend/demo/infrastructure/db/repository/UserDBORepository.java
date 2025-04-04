package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IUserRepository;
import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.db.mapper.UserDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDBORepository implements IUserRepository {
    private final SpringDataUserRepository springDataRepository;
    private final UserDBOMapper mapper;

    @Override
    public void save(User user) {
        springDataRepository.save(mapper.toDBO(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataRepository.findByEmail(email).map(mapper::toUser);
    }
}
