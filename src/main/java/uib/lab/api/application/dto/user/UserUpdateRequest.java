package uib.lab.api.application.dto.user;

import uib.lab.api.infraestructure.password.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class UserUpdateRequest {

    @Email
    @NotBlank
    private String mail;

    @NotBlank
    private String username;

    @Password
    @NotBlank
    private String password;
}
