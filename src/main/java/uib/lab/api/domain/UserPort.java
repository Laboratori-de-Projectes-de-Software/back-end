package uib.lab.api.domain;

import java.util.List;

public interface UserPort {
    UserDomain save(UserDomain user); 
    
    List<UserDomain> findAll();
}
