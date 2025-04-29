package uib.lab.api.infraestructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.util.jwt.JwtAuthenticationProvider;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiHttpResponse {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final ObjectMapper objectMapper;

    public void unauthorized(HttpServletResponse response) throws IOException {
        writeJsonResponse(response, HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    public void invalidCredentials(HttpServletResponse response) throws IOException {
        this.unauthorized(response);
    }

    public void internalServerError(HttpServletResponse response) throws IOException {
        writeJsonResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    public String jwtToken(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String token;

        if (principal instanceof UserDomain) {
            User user = new User(((UserDomain) principal).getId(), ((UserDomain) principal).getUsername(), ((UserDomain) principal).getPassword());

            token = jwtAuthenticationProvider.generate(user);
        } else {
            throw new RuntimeException("Tipo inesperado de usuario autenticado: " + principal.getClass().getName());
        }

        return token;
    }

    public JwtAuthenticationProvider getAuthenticationProvider(){
        return this.jwtAuthenticationProvider;
    }

    private void writeJsonResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        var stream = response.getOutputStream();
        var error = new ApiResponse<>(status.value(), message);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(stream, error);
        stream.flush();
    }
}