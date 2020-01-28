CREATE TABLE boletim
(
    id                   BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    id_aluno             BIGINT               NOT NULL,
    CONSTRAINT fk_boletim_aluno FOREIGN KEY (id_aluno)
    REFERENCES aluno (id)
);