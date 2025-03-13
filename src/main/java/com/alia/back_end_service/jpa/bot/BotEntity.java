package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.jpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bot")
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer bot_id;

    @ManyToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    private UserEntity user;


}
