package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.jpa.league.LeagueEntity;
import com.alia.back_end_service.jpa.message.MessageEntity;
import com.alia.back_end_service.jpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bot")
public class BotEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, unique = true)
    private String endpoint;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Relación con UserEntity
    private UserEntity user;

    @ManyToMany
    @JoinTable(name = "bot_leagues",
            joinColumns = @JoinColumn(name = "name_bot"),
            inverseJoinColumns = @JoinColumn(name = "league_id")
    )
    private List<LeagueEntity> leagues;

//    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<MessageEntity> messages;


}
