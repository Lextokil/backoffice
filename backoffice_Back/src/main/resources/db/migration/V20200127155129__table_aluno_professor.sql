create table aluno_professor
(
    id_aluno BIGINT FOREIGN KEY REFERENCES aluno(id),
    id_professor BIGINT FOREIGN KEY REFERENCES professor(id)
);