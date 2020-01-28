package com.escola.backoffice.materianota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMateriaNotas extends JpaRepository<MateriaNota, Long> {

    @Query(value = "SELECT a FROM Boletim a WHERE id like:ids ")
    List<MateriaNota> findAllMateriaNotaByIds(@Param("ids")List<Long> ids);
}
