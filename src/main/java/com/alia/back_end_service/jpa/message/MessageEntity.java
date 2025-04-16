package com.alia.back_end_service.jpa.message;



import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.game.GameEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "bot_name")
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
