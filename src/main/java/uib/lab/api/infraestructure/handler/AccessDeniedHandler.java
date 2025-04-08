package uib.lab.api.infraestructure.handler;

import uib.lab.api.infraestructure.util.ApiHttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandler implements AuthenticationEntryPoint {
    private final ApiHttpResponse apiHttpResponse;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        apiHttpResponse.unauthorized(response, request.getLocale());
    }
}
