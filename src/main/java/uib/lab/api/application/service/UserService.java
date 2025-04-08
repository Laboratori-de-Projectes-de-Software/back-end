package uib.lab.api.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.dto.user.UserUpdateRequest;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.application.dto.user.UserResponse;
import uib.lab.api.infraestructure.util.ApiMessage;
import uib.lab.api.infraestructure.util.message.MessageCode;
import uib.lab.api.infraestructure.util.message.MessageConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPort userPort;
    private final MessageConverter messageConverter;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NOT_EXISTS("user.not-exists"),
        NOT_EXISTS_BY_USERNAME("user.not-exists.username"),
        UPDATED("user.update");

        private final String code;
    }

    public UserResponse findById(int id, Locale locale) {
        return this.map(
                userPort.findById(id).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                messageConverter.getMessage(Message.NOT_EXISTS, null, locale)
                        )
                )
        );
    }

    public List<UserDomain> getAllUsers(){
        return userPort.findAll();
    }

    public ApiMessage updateUser(int id, UserUpdateRequest userUpdateRequest, Locale locale) {
        userPort.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    messageConverter.getMessage(
                            UserService.Message.NOT_EXISTS_BY_USERNAME,
                            Set.of(userUpdateRequest.getMail()),
                            locale
                    )
            );
        });

        //Si existe revisamos contraseña
        var user = mapper.map(userUpdateRequest, UserDomain.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(User.Role.USER));

        //Actualizamos el usuario usando el caso de uso de UpdateUserCase
        userPort.update(user);

        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.UPDATED, Set.of(userUpdateRequest.getUsername()), locale))
                .build();
    }

    private UserResponse map(UserDomain user) {
        return mapper.map(user, UserResponse.class);
    }
}
