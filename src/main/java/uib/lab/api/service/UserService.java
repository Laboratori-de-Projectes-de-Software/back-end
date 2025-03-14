package uib.lab.api.service;

import uib.lab.api.entity.User;
import uib.lab.api.dto.user.UserResponse;
import uib.lab.api.repository.UserRepository;
import uib.lab.api.util.message.MessageCode;
import uib.lab.api.util.message.MessageConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MessageConverter messageConverter;
    private final ModelMapper mapper;


    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NOT_EXISTS("user.not-exists");

        private final String code;
    }

    public UserResponse findById(long id, Locale locale) {
        return this.map(
                userRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                messageConverter.getMessage(Message.NOT_EXISTS, null, locale)
                        )
                )
        );
    }

    private UserResponse map(User user) {
        return mapper.map(user, UserResponse.class);
    }
}
