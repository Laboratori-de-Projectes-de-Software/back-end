package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTOLogin {
    private String email;
    private String password;
}
