package uib.lab.api.application.port;

import uib.lab.api.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    //Metodo para guardar un usuario
    UserDomain save(UserDomain user);

    //Método para recoger todos los usuarios
    List<UserDomain> findAll();
    
    //Método para actualizar un usuario
    UserDomain update(UserDomain user);

    //Método para encontrar un usuario usando su id para buscar
    Optional<UserDomain> findById(int id);

    Optional<UserDomain> findByUsername(String username);
}
