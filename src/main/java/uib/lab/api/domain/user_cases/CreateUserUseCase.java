package uib.lab.api.domain.user_cases;
import org.springframework.stereotype.Component;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.UserPort;
import uib.lab.api.entity.User;
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
