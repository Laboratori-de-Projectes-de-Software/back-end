package developers.iasuperleague.service.user;

import developers.iasuperleague.mapper.user.UserMapper;
import developers.iasuperleague.exception.NotFoundServiceException;
import developers.iasuperleague.exception.UsernameAlreadyExistsException;
import developers.iasuperleague.model.user.User;
import developers.iasuperleague.model.user.UserDTO;
import developers.iasuperleague.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundServiceException("Usuario con id: "+id+" no existe"));
    }

    public UserDTO create(UserDTO dto) {
        boolean existe = userRepository.findByUsername(dto.getUsername()).isPresent();
        if(existe) {
            throw new UsernameAlreadyExistsException("El nombre de usuario '"+dto.getUsername()+"' ya existe");
        }

        User user = userMapper.toEntity(dto);

        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }
}
