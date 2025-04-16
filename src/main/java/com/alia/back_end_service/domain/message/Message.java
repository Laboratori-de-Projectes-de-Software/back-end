package com.alia.back_end_service.domain.message;

import com.alia.back_end_service.domain.bot.Bot;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    private Integer id;

    private String message;

    private OffsetDateTime date; // Formato UTC

    private String botId;

    private Integer gameId;

}
