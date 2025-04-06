package uib.lab.api.infraestructura.util.jwt;

import uib.lab.api.domain.entity.User;
import uib.lab.api.infraestructura.jpaRepositories.UserJpaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Setter
@RequiredArgsConstructor
public abstract class JwtProvider {
    private final UserJpaRepository userRepository;

    private String secret;
    private int duration;
    private ChronoUnit temporalUnit;

    public String generate(User user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("mail", user.getMail())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plus(duration, temporalUnit)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public UsernamePasswordAuthenticationToken verify(String jwt) {
        var id = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

        return userRepository.findById(Long.valueOf(id))
                .map(user -> new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException(id));
    }
}
