package org.example.backend.databaseapi.jpa.bot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaEntity;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="bot")
public class BotJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idBot;

    @ManyToMany(mappedBy = "botsLiga")
    private List<LigaJpaEntity> ligasBot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private UsuarioJpaEntity usuario;
    private String cualidad;

    @NotBlank(message="El enlace al bot no puede estar vacio")
    private String url;
    private String imagen;

    @NotBlank(message="El nombre del bot no puede estar vacio")
    private String nombre;

}
