package uib.lab.api.infraestructura.util.jwt;

import uib.lab.api.infraestructura.JpaRepositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.verification")
public class JwtVerificationProvider extends JwtProvider {
    @Autowired
    public JwtVerificationProvider(UserJpaRepository userRepository) {
        super(userRepository);
    }
}
