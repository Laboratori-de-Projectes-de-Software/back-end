package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.User;
import uib.lab.api.domain.UserPort;
import org.springframework.stereotype.Component;

@Component
public class UserJpaAdapter implements UserPort {
    private final UserJpaRepository repo;
    private final UserMapper userMapper;

    public UserJpaAdapter(final UserJpaRepository repo, final UserMapper userMapper){
        this.repo = repo;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(
                repo.save(
                        userMapper.toEntity(user)));
    }
}
