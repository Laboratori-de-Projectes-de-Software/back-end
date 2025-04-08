package uib.lab.api.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.util.ApiMessage;
import uib.lab.api.infraestructure.util.message.MessageCode;
import uib.lab.api.infraestructure.util.message.MessageConverter;
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
public class AuthenticationService implements UserDetailsService {
    private final UserPort userPort;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public ApiMessage register(UserDTORegister userRegistrationRequest, Locale locale) {
        userPort.findByUsername(userRegistrationRequest.getUser()).ifPresent(user -> {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    messageConverter.getMessage(
                            Message.ALREADY_EXISTS,
                            Set.of(userRegistrationRequest.getUser()),
                            locale
                    )
            );
        });

        var user = strictMapper.map(userRegistrationRequest, UserDomain.class);
        user.setMail(userRegistrationRequest.getMail());
        user.setUsername(userRegistrationRequest.getUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); 
        user.setRoles(Set.of(User.Role.USER));

        userPort.save(user);

        return ApiMessage.builder()
                .status(HttpStatus.CREATED)
                .message(messageConverter.getMessage(Message.ENABLED, Set.of(userRegistrationRequest.getUser()), locale))
                .build();
    }

    public User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
