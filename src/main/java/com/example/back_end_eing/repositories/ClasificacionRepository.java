package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Clasificacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionRepository extends CrudRepository<Clasificacion, Long> {

    Clasificacion findByBotIdAndLigaId(Long botId, Long ligaId);

    List<Clasificacion> findByLigaId(Long ligaId);

    @Query("SELECT c.bot.id " +
            "FROM Clasificacion c " +
            "WHERE c.liga.id = :ligaId")
    List<Integer> findBotIdsByLigaId(@Param("ligaId") Long ligaId);
}
