package uib.lab.api.infraestructura.filter;

import uib.lab.api.infraestructura.util.ApiHttpResponse;
import uib.lab.api.infraestructura.util.jwt.JwtAuthenticationProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final ApiHttpResponse apiHttpResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || authorization.contains("null") || authorization.isBlank() || !authorization.startsWith(jwtAuthenticationProvider.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var token = authorization.replace(jwtAuthenticationProvider.getPrefix(), "").trim();
            var authentication = jwtAuthenticationProvider.verify(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            System.out.println("JWT Verification Failed: " + e.getMessage());
            apiHttpResponse.unauthorized(response, request.getLocale());
            return;
        } catch (UsernameNotFoundException e) {
            System.out.println("User Not Found: " + e.getMessage());
            apiHttpResponse.unauthorized(response, request.getLocale());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
