package uib.lab.api.application.usecase;
import org.springframework.stereotype.Component;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.port.UserPort;

@Component
public class UpdateUserUseCase {
    private final UserPort port;

    public UpdateUserUseCase(final UserPort port){
        this.port = port;
    }

    public UserDomain updateUser(Long id, final String username, final String name, final String password){
        UserDomain user = new UserDomain(id, username, name, password, true);
        return port.update(user);
    }
}
