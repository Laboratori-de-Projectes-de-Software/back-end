package uib.lab.api.domain;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class GetAllUsersUseCase {
    private final UserPort port;

    public GetAllUsersUseCase(final UserPort port){
        this.port = port;
    }

    public List<UserDomain> getUsers() {
        return port.findAll();
    }
}
