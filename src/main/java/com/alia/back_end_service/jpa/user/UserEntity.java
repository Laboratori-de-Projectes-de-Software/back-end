package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.jpa.bot.BotEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "mail", unique = true, nullable = false)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BotEntity> bots = new ArrayList<>();
}
