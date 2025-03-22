package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Bot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends CrudRepository<Bot, Long> {
}
