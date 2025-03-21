package uib.lab.api.domain;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    //Metodo para guardar un usuario
    UserDomain save(UserDomain user); 

    //Método para recoger todos los usuarios
    List<UserDomain> findAll();
    
    //Método para actualizar un usuario
    UserDomain update(UserDomain user);

    Optional<UserDomain> findById(Long id);
}
