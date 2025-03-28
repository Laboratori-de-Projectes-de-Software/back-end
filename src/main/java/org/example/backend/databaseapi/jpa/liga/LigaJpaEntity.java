package org.example.backend.databaseapi.jpa.liga;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaEntity;

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

    @ManyToOne(optional = false)
    @JoinColumn(name="id_usuario")
    private UsuarioJpaEntity usuario;

    @NotBlank(message = "El nombre de la liga no puede estar vacia")
    private String nombre;

}
