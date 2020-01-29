package com.escola.backoffice.turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITurmaRepository extends JpaRepository<Turma, Long> {

    @Query(value = "SELECT t FROM Turma t WHERE id like :ids ")
    List<Turma> findAllTurmasByIds(@Param("ids")List<Long> ids);
}
