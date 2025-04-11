package jaumesitos.backend.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class League {
    private Integer matchTime;
    private String name;
    private Integer rounds;
    private String urlImage;
    private Boolean state;
    private Integer bots[];
    private Integer userId;
}
