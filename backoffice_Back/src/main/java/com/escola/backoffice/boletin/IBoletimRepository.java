package com.escola.backoffice.boletin;

import com.escola.backoffice.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBoletimRepository extends JpaRepository<Boletim, Long> {

    @Query(value = "select p from Boletim p where p.id in :ids ")
    List<Boletim> findAllBoletimByIds(@Param("ids")List<Long> ids);

    List<BoletimDTO> findAllByAluno(Aluno id);
}
