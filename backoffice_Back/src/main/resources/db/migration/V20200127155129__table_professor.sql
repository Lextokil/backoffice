

CREATE TABLE professor
(
    id                   BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    nome                 VARCHAR(50)          NOT NULL,
    materia              VARCHAR(30)          NOT NULL
);