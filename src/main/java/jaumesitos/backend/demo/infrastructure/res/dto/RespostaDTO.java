package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RespostaDTO {
    private String id;
    private String argument;
    private LocalDateTime date;
}
