package com.adondeband.back_end_adonde_band.API.mensaje;

import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MensajeDtoMapper {
    MensajeDtoMapper INSTANCE = Mappers.getMapper(MensajeDtoMapper.class);

    // Mapear de DTO a Dominio
    Mensajes.Mensaje toDomain(MensajeDTO mensajeDTO);

    // Mapea de Bot a BotDTO
    MensajeDTO toDTO(Mensajes.Mensaje mensaje);

    // Mapea de lista de Mensaje a lista de MensajeDTO
    default List<MensajeDTO> toListDTO(Mensajes mensajes) {
        if (mensajes == null || mensajes.getMsgsL() == null || mensajes.getMsgsV() == null) {
            return null;
        }

        // Convertir los mensajes a MensajeDTO
        List<MensajeDTO> mensajesDTO = new ArrayList<>();
        for (int i = 0; i < (mensajes.getMsgsL().size() + mensajes.getMsgsV().size()); i++) {
            Mensajes.Mensaje mensaje;
            if (i % 2 == 0) {
                mensaje = mensajes.getMsgsL().get(i / 2);
            } else {
                mensaje = mensajes.getMsgsV().get(i / 2);
            }
            mensajesDTO.add(new MensajeDTO(mensaje.getMensaje(), mensaje.getIdBot(), mensaje.getTimestamp()));
        }
        return mensajesDTO;
    }
}
