package com.escola.backoffice.professor;

import com.escola.backoffice.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProfessoreRepository extends JpaRepository<Professor,Long> {

    @Query(value = "SELECT a FROM Professor a WHERE id in:ids ")
    List<Professor> findAllProfessorByIds(@Param("ids")List<Long> ids);

    List<ProfessorDTO> findAllByTurmas(Turma id);


}
