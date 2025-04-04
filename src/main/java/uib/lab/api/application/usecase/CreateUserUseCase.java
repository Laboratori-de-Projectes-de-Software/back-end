package uib.lab.api.application.usecase;
import org.springframework.stereotype.Component;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.entity.User;
import java.util.Set;

@Component
public class CreateUserUseCase {
    private final UserPort port;

    public CreateUserUseCase(final UserPort port){
        this.port = port;
    }

    public UserDomain createUser(final String username, final String name, final String password, final Set<User.Role> roles) {
        UserDomain user = new UserDomain(username, name, password, true, roles);
        return port.save(user);
    }
}
