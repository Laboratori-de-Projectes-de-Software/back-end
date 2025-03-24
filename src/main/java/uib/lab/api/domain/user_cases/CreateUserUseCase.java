package uib.lab.api.domain.user_cases;
import org.springframework.stereotype.Component;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.UserPort;

@Component
public class CreateUserUseCase {
    private final UserPort port;

    public CreateUserUseCase(final UserPort port){
        this.port = port;
    }

    public UserDomain createUser(final String username, final String name, final String password){
        UserDomain user = new UserDomain(username, name, password);
        return port.save(user);
    }
}
