create table professor_turma
(
    id_professor BIGINT FOREIGN KEY REFERENCES professor(id),
    id_turma BIGINT FOREIGN KEY REFERENCES turma(id)
);