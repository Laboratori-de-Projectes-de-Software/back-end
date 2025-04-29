package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.jpa.classification.ClassificationEntity;
import com.alia.back_end_service.jpa.game.GameEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, unique = true)
    private String endpoint;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = true)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Relación con UserEntity
    private UserEntity user;

    @ManyToMany(mappedBy = "bots")
    private List<LeagueEntity> leagues;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MessageEntity> messages;

    @OneToMany(mappedBy = "localBot", fetch = FetchType.LAZY)
    private List<GameEntity> localGames;

    @OneToMany(mappedBy = "visitorBot", fetch = FetchType.LAZY)
    private List<GameEntity> visitorGames;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClassificationEntity> classifications;

}
