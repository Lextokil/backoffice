CREATE TABLE turma
(
    id                   BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    turno                VARCHAR(15)          NOT NULL
);



CREATE TABLE aluno
(
    id                   BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    nome                 VARCHAR(50)          NOT NULL,
    turno                VARCHAR(15)          NOT NULL,
    id_turma                                    BIGINT,
    CONSTRAINT fk_aluno_turma FOREIGN KEY (id_turma)
    REFERENCES turma (id)
);