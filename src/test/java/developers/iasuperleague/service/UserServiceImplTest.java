package developers.iasuperleague.service;

import developers.iasuperleague.exception.UsernameAlreadyExistsException;
import developers.iasuperleague.mapper.user.UserMapper;
import developers.iasuperleague.model.user.User;
import developers.iasuperleague.model.user.UserDTO;
import developers.iasuperleague.repository.user.UserRepository;
import developers.iasuperleague.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl service;

    private long id;

    @BeforeEach
    public void setUp() {
        id = 1L;
    }

    @Test
    public void findAllTest() {
        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setPassword("password");
        when(userRepository.findAll()).thenReturn(List.of(user));

        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setUsername("username");
        dto.setPassword("password");

        when(userMapper.toDTO(user)).thenReturn(dto);

        List<UserDTO> result = service.findAll();
        System.out.println(result.get(0).getUsername());
        assertNotNull(result);
    }

    @Test
    public void findByIdTest() {
        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setPassword("password");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setUsername("username");
        dto.setPassword("password");

        when(userMapper.toDTO(user)).thenReturn(dto);

        UserDTO result = service.findById(id);
        assertNotNull(result);
    }

    @Test
    public void createTest() {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setUsername("username");
        dto.setPassword("password");

        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setPassword("password");

        when(userRepository.findByUsername(dto.getUsername())).thenReturn(Optional.empty());

        when(userMapper.toEntity(dto)).thenReturn(user);

        when(userRepository.save(user)).thenReturn(user);

        when(userMapper.toDTO(user)).thenReturn(dto);

        UserDTO result = service.create(dto);
        assertNotNull(result);
    }

    @Test
    public void createExceptionTest() {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setUsername("username");
        dto.setPassword("password");

        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setPassword("password");

        when(userRepository.findByUsername(dto.getUsername())).thenReturn(Optional.of(user));

        assertThrows(UsernameAlreadyExistsException.class, () ->
            service.create(dto)
        );
    }

}
