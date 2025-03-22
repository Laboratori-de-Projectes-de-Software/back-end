package com.example.back_end_eing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    //******* COLUMNAS *******
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nick;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String foto;

    //******* CONSTRUCTORES *******
    public Usuario() {
    }

    //******* RELACIONES CON OTRAS CLASES *******
    @OneToMany(
            mappedBy = "usuario",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private Set<Bot> bots = new HashSet<>();
}
