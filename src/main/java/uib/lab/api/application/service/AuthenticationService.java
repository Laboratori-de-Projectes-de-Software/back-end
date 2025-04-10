package uib.lab.api.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Set;

@Setter
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper strictMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public ApiResponse<?> register(UserDTORegister userRegistrationRequest) {
        try {
            userPort.findByUsername(userRegistrationRequest.getUser()).ifPresent(user -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            });

            var user = strictMapper.map(userRegistrationRequest, UserDomain.class);
            user.setMail(userRegistrationRequest.getMail());
            user.setUsername(userRegistrationRequest.getUser());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            user.setRoles(Set.of(User.Role.USER));

            userPort.save(user);

            return new ApiResponse<>(200, "User created");

        } catch (ResponseStatusException ex) {
            if (ex.getStatus() == HttpStatus.CONFLICT) {
                return new ApiResponse<>(409, "User already exists");
            }
            return new ApiResponse<>(500, "Internal Server Error");
        } catch (Exception ex) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }
}
