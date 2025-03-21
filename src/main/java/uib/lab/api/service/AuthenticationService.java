package uib.lab.api.service;

import uib.lab.api.dto.user.UserRegistrationRequest;
import uib.lab.api.entity.User;
import uib.lab.api.entity.jpa_user.UserJpaAdapter;
import uib.lab.api.entity.jpa_user.UserMapper;
import uib.lab.api.entity.jpa_user.UserJpaRepository;
import uib.lab.api.util.ApiMessage;
import uib.lab.api.util.jwt.JwtVerificationProvider;
import uib.lab.api.util.message.MessageCode;
import uib.lab.api.util.message.MessageConverter;
import uib.lab.api.domain.CreateUserUseCase;
import uib.lab.api.domain.GetAllUsersUseCase;
import uib.lab.api.domain.UserDomain;

import uib.lab.api.domain.UserPort;
import io.jsonwebtoken.MalformedJwtException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.util.Locale;
import java.util.Set;
import java.util.List;

@Setter
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final UserJpaAdapter userJpaAdapter;
    private final CreateUserUseCase createUserCase;
    private final GetAllUsersUseCase getAllUserCase;

    private final JwtVerificationProvider jwtVerificationProvider;

    private final PasswordEncoder passwordEncoder;

    private final MessageConverter messageConverter;

    private final ModelMapper strictMapper;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        ALREADY_EXISTS("user.already-exists.username"),
        NOT_EXISTS_BY_USERNAME("user.not-exists.username"),
        ENABLED("registration.user-enabled");

        private final String code;
    }

    public ApiMessage register(UserRegistrationRequest userRegistrationRequest, Locale locale) throws MessagingException {
        userJpaRepository.findByUsername(userRegistrationRequest.getUsername()).ifPresent(user -> {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    messageConverter.getMessage(
                            Message.ALREADY_EXISTS,
                            Set.of(userRegistrationRequest.getUsername()),
                            locale
                    )
            );
        });

        var user = strictMapper.map(userRegistrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setEnabled(true); 
        user.setRoles(Set.of(User.Role.USER));

        //Creamos el usuario usando el caso de uso de CreateUserCase
        createUserCase.createUser(user.getUsername(), user.getName(), user.getPassword());

        //Adaptador para guardar el usuario en la base de datos
        //userJpaAdapter.save(userMapper.toDomain(user));

        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.ENABLED, Set.of(userRegistrationRequest.getName()), locale))
                .build();
    }

    /*
     * Método con el que recoger todos los usuarios de la base de datos
     */
    public List<UserDomain> getAllUsers(){
        List<UserDomain> usuarios = getAllUserCase.getUsers();
        return usuarios;
    }

    public User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
