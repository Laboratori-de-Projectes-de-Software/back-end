package jaumesitos.backend.demo.infrastructure.db.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserDBO {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
