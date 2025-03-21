package uib.lab.api.domain;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserUseCase {
    private final UserPort port;

    public UpdateUserUseCase(final UserPort port){
        this.port = port;
    }

    public UserDomain updateUser(Long id, final String username, final String name, final String password){
        UserDomain user = new UserDomain(id, username, name, password);
        return port.update(user);
    }
}
