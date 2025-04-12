package org.example.backend.databaseapi.jpa.liga;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@Table(name="liga")
public class LigaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_liga")
    private Integer ligaId;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bot_liga",
            joinColumns = @JoinColumn(name = "id_liga"),
            inverseJoinColumns = @JoinColumn(name = "id_bot"))
    private List<BotJpaEntity> botsLiga;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_usuario")
    private UsuarioJpaEntity usuario;

    @NotBlank(message = "El nombre de la liga no puede estar vacia")
    private String nombre;

    @Column(name="match_time")
    @Lob
    private Long matchTime;

    @Column(name="url_imagen")
    private String urlImagen;

    private int rondas;
}
