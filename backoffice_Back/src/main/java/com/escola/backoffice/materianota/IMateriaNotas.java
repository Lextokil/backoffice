package com.escola.backoffice.materianota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaNotas extends JpaRepository<MateriaNota, Long> {

}
