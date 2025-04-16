package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTOLogin {
    private String user;
    private String password;
}
