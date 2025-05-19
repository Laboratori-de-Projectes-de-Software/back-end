package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.bot.BotDTO;
import com.debateia.adapter.in.rest.bot.BotResponseDTO;
import com.debateia.adapter.in.rest.bot.BotSummaryResponseDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.user.UserEntity;
import com.debateia.domain.Bot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BotMapper {
    
    BotMapper INSTANCE = Mappers.getMapper(BotMapper.class);
    
    BotSummaryResponseDTO toSummaryDTO(Bot bot);

    @Mapping(target = "NDraws", constant = "0")
    @Mapping(target = "NLosses", constant = "0")
    @Mapping(target = "NWins", constant = "0")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Bot DTOtoDomain(BotDTO dto);
    

    @Mapping(target = "userId", source = "user", qualifiedByName = "extractUserId")
    Bot EntityToDomain(BotEntity entity);
    
    @Mapping(target = "user", source = "userId", qualifiedByName = "createUserEntity")
    @Mapping(target = "nLosses", source = "NLosses")
    @Mapping(target = "nWins", source = "NWins")
    @Mapping(target = "nDraws", source = "NDraws")
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "participations", ignore = true)
    BotEntity toEntity(Bot bot);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "imageUrl", source = "urlImagen")
    BotResponseDTO toResponseDto(Bot bot);
    
    @Named("extractUserId")
    default Integer extractUserId(UserEntity user) {
        return user == null ? null : user.getId();
    }
    
    @Named("createUserEntity")
    default UserEntity createUserEntity(Integer userId) {
        if (userId == null) return null;
        UserEntity dummy = new UserEntity();
        dummy.setId(userId);
        return dummy;
    }
}