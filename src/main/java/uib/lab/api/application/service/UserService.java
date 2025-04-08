package uib.lab.api.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.usecase.GetAllUsersUseCase;
import uib.lab.api.application.usecase.UpdateUserUseCase;
import uib.lab.api.application.dto.user.UserUpdateRequest;
import uib.lab.api.infraestructure.entity.User;
import uib.lab.api.infraestructure.jpaRepositories.UserJpaRepository;
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
import javax.mail.MessagingException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final MessageConverter messageConverter;
    private final ModelMapper mapper;
    private final GetAllUsersUseCase getAllUserCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NOT_EXISTS("user.not-exists"),
        NOT_EXISTS_BY_USERNAME("user.not-exists.username"),
        UPDATED("user.update");

        private final String code;
    }

    public UserResponse findById(long id, Locale locale) {
        return this.map(
                userJpaRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                messageConverter.getMessage(Message.NOT_EXISTS, null, locale)
                        )
                )
        );
    }

    public List<UserDomain> getAllUsers(){
        return getAllUserCase.getUsers();
    }

    public ApiMessage updateUser(Long id, UserUpdateRequest userUpdateRequest, Locale locale) throws MessagingException {
        userJpaRepository.findById(id).orElseThrow(() -> {
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
        var user = mapper.map(userUpdateRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(User.Role.USER));

        //Actualizamos el usuario usando el caso de uso de UpdateUserCase
        updateUserUseCase.updateUser(id, user.getMail(), user.getUsername(), user.getPassword());

        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.UPDATED, Set.of(userUpdateRequest.getUsername()), locale))
                .build();
    }

    private UserResponse map(User user) {
        return mapper.map(user, UserResponse.class);
    }
}
