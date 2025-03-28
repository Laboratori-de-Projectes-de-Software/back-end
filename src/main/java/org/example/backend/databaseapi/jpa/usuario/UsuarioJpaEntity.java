package org.example.backend.databaseapi.jpa.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="usuario")
public class UsuarioJpaEntity {

    @Id
    @GeneratedValue
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name="id_usuario")
    private Integer userId;

    @NotBlank(message="El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message="El nombre no puede estar vacio")
    private String email;

    @Column(name="contraseña")
    @NotBlank(message="El nombre no puede estar vacio")
    private String password;

    private String imagen;

}
