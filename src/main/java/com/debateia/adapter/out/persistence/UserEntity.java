package com.debateia.adapter.out.persistence;

import com.debateia.domain.User;
import io.jsonwebtoken.security.Message;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_user")
public class UserEntity {
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
    private MatchEntity match;

    // mapeo al atributo "user" de la clase AIEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AIEntity> aiList = new ArrayList<>();

    
    public UserEntity() {}
    
    // Convierte una clase User del dominio a una clase UserEntity (JPA)
    public UserEntity(User dom) {
        
    }
    
    // Convierte una clase UserEntity (JPA) a una clase User, del dominio de la aplicacion
    public User toDomain() {
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
