package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Extiende JpaRepository para que spring genere consultas automaticamente */
@Repository
public interface BotJpaRepository extends JpaRepository<BotEntity, Integer> {
    List<BotEntity> findByUser_Id(Integer userId); /* Spring Data JPA genera la consulta automaticamente */
    boolean existsByName(String name);
}
