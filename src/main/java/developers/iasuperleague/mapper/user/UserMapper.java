package developers.iasuperleague.mapper.user;

import developers.iasuperleague.model.user.User;
import developers.iasuperleague.model.user.UserDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

//TODO: Añadir los Mappers de BOT una vez hechos
@Mapper(componentModel = "spring"/*, uses = {BotMapper.class, LigaMapper.class}*/)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);
}
