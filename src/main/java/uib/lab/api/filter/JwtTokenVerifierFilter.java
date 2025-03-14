package uib.lab.api.filter;

import uib.lab.api.util.ApiHttpResponse;
import uib.lab.api.util.jwt.JwtAuthenticationProvider;
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
            SecurityContextHolder.getContext().setAuthentication(
                    jwtAuthenticationProvider.verify(
                            authorization.replace(jwtAuthenticationProvider.getPrefix(), "")
                    )
            );
        } catch (JwtException | UsernameNotFoundException e) {
            apiHttpResponse.unauthorized(response, request.getLocale());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
