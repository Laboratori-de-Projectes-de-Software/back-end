package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Participacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacionRepository extends CrudRepository<Participacion, Long> {
}
