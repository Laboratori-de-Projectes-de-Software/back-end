package uib.lab.api.infraestructure.util.jwt;

import uib.lab.api.application.port.UserPort;
import uib.lab.api.infraestructure.jpaRepositories.UserJpaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.authentication")
public class JwtAuthenticationProvider extends JwtProvider {
    @Setter
    @Getter
    @Value("${jwt.prefix}")
    private String prefix;

    @Autowired
    public JwtAuthenticationProvider(UserPort userPort) {
        super(userPort);
    }
}
