package com.escola.backoffice.turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ITurmaRepository extends JpaRepository<Turma, Long> {

    @Query(value = "SELECT t FROM Turma t WHERE t.id in :ids")
    Set<Turma> findAllTurmasByIds(@Param("ids") List<Long> ids);
}
