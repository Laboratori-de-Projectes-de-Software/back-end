package com.debateia.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Extiende JpaRepository para que spring genere consultas automaticamente */
@Repository
public interface AIJpaRepository extends JpaRepository<AIEntity, Integer> {
    List<AIEntity> findByUser_Id(Integer userId); /* Spring Data JPA genera la consulta automaticamente */
}
