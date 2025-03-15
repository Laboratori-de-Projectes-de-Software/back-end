package com.debateia.jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_ai")
public class AIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String trait;

    @Column(nullable = false)
    private  String secret;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // clave foranea a id de usuario
    private UserEntity user;

    // mappedBy coincide con el atributo en la clase MessageEntity
    @OneToMany(mappedBy = "ai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageEntity> messages = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

}
