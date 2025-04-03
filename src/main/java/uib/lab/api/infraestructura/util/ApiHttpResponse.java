package uib.lab.api.infraestructura.util;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.entity.User;
import uib.lab.api.infraestructura.util.jwt.JwtAuthenticationProvider;
import uib.lab.api.infraestructura.util.message.MessageCode;
import uib.lab.api.infraestructura.util.message.MessageConverter;
import uib.lab.api.util.message.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ApiHttpResponse {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MessageConverter messageConverter;
    private final ObjectMapper objectMapper;

    @Getter
    @RequiredArgsConstructor
    private enum Message implements MessageCode {
        NOT_AUTHORIZED("user.not-authorized"),
        INVALID_CREDENTIALS("user.invalid-credentials");

        private final String code;
    }

    public void unauthorized(HttpServletResponse response, Locale locale) throws IOException {
        this.unauthorized(Message.NOT_AUTHORIZED, response, locale);
    }

    public void invalidCredentials(HttpServletResponse response, Locale locale) throws IOException {
        this.unauthorized(Message.INVALID_CREDENTIALS, response, locale);
    }

    public void jwtToken(HttpServletResponse response, Authentication authentication) throws IOException {
        Object principal = authentication.getPrincipal();
        String token;

        if (principal instanceof UserDomain) {
            User user = new User(((UserDomain) principal).getId(), ((UserDomain) principal).getUsername(), ((UserDomain) principal).getPassword());

            token = jwtAuthenticationProvider.generate(user);
        } else {
            throw new RuntimeException("Tipo inesperado de usuario autenticado: " + principal.getClass().getName());
        }

        var stream = response.getOutputStream();
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        objectMapper.writeValue(stream, token);
        stream.flush();
    }

    private void unauthorized(Message message, HttpServletResponse response, Locale locale) throws IOException {
        var stream = response.getOutputStream();
        var error = ApiMessage.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(messageConverter.getMessage(message, null, locale))
                .build();

        response.setStatus(error.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(stream, error);
        stream.flush();
    }
}
