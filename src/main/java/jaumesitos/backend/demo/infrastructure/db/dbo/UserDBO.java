package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@Table(name = "usuari")
@NoArgsConstructor
@Entity
public class UserDBO {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
}
