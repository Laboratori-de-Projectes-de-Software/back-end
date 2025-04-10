package com.debateia.adapter.out.persistence.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(name = "urlImagen")
    private String urlImage;

    private String endpoint;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "n_wins")
    private Integer wins;

    @Column(name = "n_losses")
    private Integer losses;

    @Column(name = "n_draws")
    private Integer draws;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageEntity> messages = new ArrayList<>();
}
