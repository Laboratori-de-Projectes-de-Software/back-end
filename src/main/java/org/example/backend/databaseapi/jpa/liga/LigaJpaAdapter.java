package org.example.backend.databaseapi.jpa.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.liga.CreateLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindAllLigasPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaUsuarioPort;
import org.example.backend.databaseapi.domain.Liga;
import org.example.backend.databaseapi.domain.Usuario;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class LigaJpaAdapter implements CreateLigaPort, FindAllLigasPort, FindLigaPort, FindLigaUsuarioPort {

    private final LigaJpaMapper ligaJpaMapper;
    private final LigaJpaRepository ligaJpaRepository;
    private final UsuarioJpaMapper usuarioJpaMapper;

    @Override
    public Liga createLiga(Liga liga) {
        return ligaJpaMapper.toDomain(
                ligaJpaRepository.save(
                        ligaJpaMapper.toEntity(liga)
                )
        );
    }

    @Override
    public List<Liga> findAllLigas() {
        return ligaJpaRepository.findAll()
                .stream()
                .map(ligaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Liga> findLiga(int id_liga) {
        return ligaJpaRepository.findById(id_liga)
                .map(ligaJpaMapper::toDomain);

    }

    @Override
    public List<Liga> findLigasUsuario(int id_usuario) {
        return ligaJpaRepository.findByUsuario_UserId(id_usuario)
                .stream()
                .map(ligaJpaMapper::toDomain)
                .toList();
    }
}
