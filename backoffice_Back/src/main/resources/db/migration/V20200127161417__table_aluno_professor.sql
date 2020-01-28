create table aluno_professor
(
    id                  BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    id_aluno                                                BIGINT,
    id_professor                                            BIGINT,
    CONSTRAINT fk_ap_aluno                   FOREIGN KEY (id_aluno)
    REFERENCES aluno (id),
    CONSTRAINT fk_ap_professor           FOREIGN KEY (id_professor)
    REFERENCES professor (id)
);