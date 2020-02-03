package com.escola.backoffice.professor;

import com.escola.backoffice.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProfessoreRepository extends JpaRepository<Professor,Long> {

    @Query(value = "SELECT a FROM Professor a WHERE id like:ids ")
    List<Professor> findAllProfessorByIds(@Param("ids")List<Long> ids);
}
