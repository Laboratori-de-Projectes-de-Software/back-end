package com.adondeband.back_end_adonde_band.dominio.conversacion;

import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;

public interface ConversacionService {

    Conversacion crearConversacion(Conversacion conversacion);
    Mensajes obtenerMensajes(String rutaFichero);

}
