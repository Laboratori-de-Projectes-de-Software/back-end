package developers.iasuperleague.model.user;

import developers.iasuperleague.model.bot.Bot;
import developers.iasuperleague.model.liga.Liga;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Liga> ligasCreadas;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Bot> bots;
}
