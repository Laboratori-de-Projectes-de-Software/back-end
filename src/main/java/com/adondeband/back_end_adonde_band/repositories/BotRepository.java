package com.adondeband.back_end_adonde_band.repositories;

import com.adondeband.back_end_adonde_band.api.modelos.Bot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotRepository extends CrudRepository<Bot, Integer> {

    List<Bot> findByNombre(String nombre);
}
