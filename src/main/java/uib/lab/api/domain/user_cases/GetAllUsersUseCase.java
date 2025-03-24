package uib.lab.api.domain.user_cases;
import org.springframework.stereotype.Component;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.UserPort;

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
