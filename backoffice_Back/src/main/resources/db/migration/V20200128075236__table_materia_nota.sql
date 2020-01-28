CREATE TABLE materia_nota
(
    id          BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    materia         VARCHAR(25)                  NOT NULL,
    materia_nota                             decimal(5,2),
    id_boletim                                     BIGINT,
    CONSTRAINT fk_mn_boletim FOREIGN KEY (id_boletim)
    REFERENCES boletim (id)
);