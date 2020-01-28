create table professor_turma
(   id BIGINT                      IDENTITY(1,1) PRIMARY KEY,
    id_professor BIGINT FOREIGN KEY REFERENCES professor(id),
    id_turma         BIGINT FOREIGN KEY REFERENCES turma(id),
    CONSTRAINT fk_pt_pressor       FOREIGN KEY (id_professor)
    REFERENCES professor (id),
    CONSTRAINT fk_pt_turma             FOREIGN KEY (id_turma)
    REFERENCES turma (id)
);