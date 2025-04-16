package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.*;
import jaumesitos.backend.demo.infrastructure.db.config.IntegerArrayToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lliga")
public class LeagueDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "match_time")
    private Integer matchTime;
    private String name;
    private Integer rounds;
    @Column(name = "url_image")
    private String urlImagen;
    private String state;
    @Convert(converter = IntegerArrayToJsonConverter.class)
    private Integer bots[];
    @Column(name = "owner_id")
    private Integer userId;
}
