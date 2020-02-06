package com.escola.backoffice.aluno;

import com.escola.backoffice.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "SELECT a FROM Aluno a WHERE a.id in :ids ")
    Set<Aluno> findAllAlunosByIds(@Param("ids")List<Long> ids);


    Set<AlunoDTO> findAllByTurma(Turma id);

}
