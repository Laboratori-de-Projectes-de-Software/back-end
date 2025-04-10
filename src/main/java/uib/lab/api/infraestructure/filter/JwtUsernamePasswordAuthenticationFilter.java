package uib.lab.api.infraestructure.filter;

import io.jsonwebtoken.Jwts;
import uib.lab.api.application.dto.user.UserDTOLogin;
import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.util.ApiHttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final ApiHttpResponse apiHttpResponse;
    private final AuthenticationManager authenticationManager;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var user = objectMapper.readValue(request.getInputStream(), UserDTOLogin.class);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUser(), user.getPassword())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication authentication) throws IOException {
        try {

            UserDomain user = (UserDomain) authentication.getPrincipal();
            String token = apiHttpResponse.jwtToken(authentication);

            Date exp = Jwts.parserBuilder()
                    .setSigningKey(apiHttpResponse.getAuthenticationProvider().getSecret().getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            UserResponseDTO dto = new UserResponseDTO();
            dto.setUserId(user.getId());
            dto.setUser(user.getUsername());
            dto.setToken(token);
            dto.setExpiresIn((exp.getTime())/1000);

            ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>(200, "User logged", dto);

            response.setContentType("application/json");
            objectMapper.writeValue(response.getOutputStream(), apiResponse);

        } catch (Exception e) {
            apiHttpResponse.internalServerError(response);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException e) throws IOException {
        apiHttpResponse.invalidCredentials(response);
    }
}