package com.debateia.adapter.out.bot;


import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.adapter.out.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_bot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bot_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "url_imagen")
    private String urlImagen;

    private String endpoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "n_wins")
    private Integer nWins;

    @Column(name = "n_losses")
    private Integer nLosses;

    @Column(name = "n_draws")
    private Integer nDraws;

    @OneToMany(mappedBy = "bot", fetch = FetchType.LAZY)
    private List<MessageEntity> messages;
    
    @OneToMany(mappedBy = "bot", fetch = FetchType.LAZY)
    private List<ParticipationEntity> participations;
}
