package com.escola.backoffice.aluno;

import com.escola.backoffice.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "SELECT a FROM Aluno a WHERE id like:ids ")
    List<Aluno> findAllAlunosByIds(@Param("ids")List<Long> ids);


    List<AlunoDTO> findAllByTurma(Turma id);
}
