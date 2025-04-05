package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Clasificacion;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionRepository extends CrudRepository<Clasificacion, Long> {

    Clasificacion findByBotIdAndLigaId(Long botId, Long ligaId);

    List<Clasificacion> findByLigaId(Long ligaId);
}
