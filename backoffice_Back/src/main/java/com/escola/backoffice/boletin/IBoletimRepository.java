package com.escola.backoffice.boletin;

import com.escola.backoffice.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBoletimRepository extends JpaRepository<Boletim, Long> {

    @Query(value = "SELECT a FROM Boletim a WHERE id like:ids ")
    List<Boletim> findAllBoletimByIds(@Param("ids")List<Long> ids);

    List<BoletimDTO> findAllByAluno(Aluno id);
}
