package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversacionJpaRepository extends JpaRepository<ConversacionEntity, Integer> {
    ConversacionEntity findByFicheroRuta(String rutaFichero);

}
