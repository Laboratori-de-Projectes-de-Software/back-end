package uib.lab.api.domain;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {
    private final UserPort port;

    public CreateUserUseCase(final UserPort port){
        this.port = port;
    }

    public User createUser(final String username, final String name, final String password){
        User user = new User(username, name, password);
        return port.save(user);
    }
}
