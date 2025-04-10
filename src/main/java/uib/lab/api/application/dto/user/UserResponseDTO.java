package uib.lab.api.application.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String user;
    private String token;
    private Long expiresIn;
}