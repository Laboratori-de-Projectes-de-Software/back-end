package com.debateia.repository_viejo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.debateia.model_viejo.Debate;

@Repository
public interface DebateRepository extends JpaRepository<Debate, Long> {
    // Buscar debates por tema
    List<Debate> findByTopic(String topic);
    
    // Buscar debates activos
    List<Debate> findByIsActiveTrue();
    
    // Buscar debates por fecha de creación
    List<Debate> findByCreatedAtAfter(LocalDateTime date);
    
    // Buscar debates con mensajes rendidos
    @Query("SELECT d FROM Debate d JOIN d.messages m WHERE m.hasYielded = true")
    List<Debate> findDebatesWithYieldedMessages();
}