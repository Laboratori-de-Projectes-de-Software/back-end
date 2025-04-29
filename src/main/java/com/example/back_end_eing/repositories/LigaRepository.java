package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Long> {
    @Query(value = "SELECT * FROM liga WHERE usuario_id = :userId", nativeQuery = true)
    List<Liga> findAllByUserId(@Param("userId")Long userId);
}
