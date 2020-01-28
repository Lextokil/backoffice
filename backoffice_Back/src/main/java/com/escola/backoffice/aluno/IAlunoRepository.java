package com.escola.backoffice.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "SELECT a FROM Aluno a WHERE id like:ids ")
    List<Aluno> findAllAlunosByIds(@Param("ids")List<Long> ids);
}
