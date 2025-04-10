package uib.lab.api.infraestructure.util.jwt;

import uib.lab.api.application.port.UserPort;
import uib.lab.api.infraestructure.jpaEntity.User;
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
    private final UserPort userPort;

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

        return userPort.findById(Integer.parseInt(id))
                .map(user -> new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException(id));
    }

    public String getSecret(){
        return this.secret;
    }
}
