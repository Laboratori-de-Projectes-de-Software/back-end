package com.example.back_end_eing.repositories;

import java.util.Optional;
import com.example.back_end_eing.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
