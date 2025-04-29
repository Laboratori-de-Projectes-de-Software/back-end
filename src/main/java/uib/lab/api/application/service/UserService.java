package uib.lab.api.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.dto.user.UserUpdateRequest;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.Set;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPort userPort;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO findById(int id) {
        return this.map(
                userPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id))
        );
    }

    public ApiResponse updateUser(int id, UserUpdateRequest userUpdateRequest) {
        userPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        var user = mapper.map(userUpdateRequest, UserDomain.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(User.Role.USER));

        userPort.update(user);

        return new ApiResponse(201, "User updated");
    }

    private UserResponseDTO map(UserDomain user) {
        return mapper.map(user, UserResponseDTO.class);
    }
}
