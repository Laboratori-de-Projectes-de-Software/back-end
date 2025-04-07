package uib.lab.api.application.service;

import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.domain.entity.User;
import uib.lab.api.infraestructura.jpaRepositories.UserJpaRepository;
import uib.lab.api.infraestructura.util.ApiMessage;
import uib.lab.api.infraestructura.util.message.MessageCode;
import uib.lab.api.infraestructura.util.message.MessageConverter;
import uib.lab.api.application.usecase.CreateUserUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Locale;
import java.util.Set;

@Setter
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserJpaRepository userJpaRepository;
    private final CreateUserUseCase createUserCase;
    private final PasswordEncoder passwordEncoder;
    private final MessageConverter messageConverter;
    private final ModelMapper strictMapper;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        ALREADY_EXISTS("user.already-exists.username"),
        NOT_EXISTS_BY_USERNAME("user.not-exists.username"),
        ENABLED("registration.user-enabled"),
        UPDATED("user.update");
        private final String code;
    }

    public ApiMessage register(UserDTORegister userRegistrationRequest, Locale locale) {
        userJpaRepository.findByUsername(userRegistrationRequest.getMail()).ifPresent(user -> {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    messageConverter.getMessage(
                            Message.ALREADY_EXISTS,
                            Set.of(userRegistrationRequest.getMail()),
                            locale
                    )
            );
        });

        var user = strictMapper.map(userRegistrationRequest, User.class);
        user.setUsername(userRegistrationRequest.getUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); 
        user.setRoles(Set.of(User.Role.USER));

        //Creamos el usuario usando el caso de uso de CreateUserCase
        createUserCase.createUser(user.getMail(), user.getUsername(), user.getPassword(), user.getRoles());

        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.ENABLED, Set.of(userRegistrationRequest.getUser()), locale))
                .build();
    }

    public User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
