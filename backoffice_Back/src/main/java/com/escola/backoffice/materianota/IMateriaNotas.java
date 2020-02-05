package com.escola.backoffice.materianota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMateriaNotas extends JpaRepository<MateriaNota, Long> {

    @Query(value = "SELECT a FROM Boletim a WHERE id in:ids ")
    List<MateriaNota> findAllMateriaNotaByIds(@Param("ids")List<Long> ids);
}
