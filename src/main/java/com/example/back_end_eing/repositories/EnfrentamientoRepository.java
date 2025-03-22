package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Enfrentamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfrentamientoRepository extends CrudRepository<Enfrentamiento, Long> {
}
