package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class UserEntityTEsting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "match_id", nullable = false) // clave foranea a id de match
    private LeagueEntity match;

    // mapeo al atributo "user" de la clase AIEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BotEntity> aiList = new ArrayList<>();

    public UserEntityTEsting() {
    }

    // Convierte una clase User del dominio a una clase UserEntity (JPA)
    public UserEntityTEsting(UserEntity dom) {

    }

    // Convierte una clase UserEntity (JPA) a una clase User, del dominio de la
    // aplicacion
    public UserEntity toDomain() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUser() {
        return username;
    }

    public void setNameUser(String nameUser) {
        this.username = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
