CREATE TABLE
(
    id                   BIGINT IDENTITY(1,1) NOT NULL,
    nome                 VARCHAR(50)          NOT NULL,
    turno                VARCHAR(15)          NOT NULL,
    id_turma    BIGINT FOREIGN KEY REFERENCES turma(id)
);