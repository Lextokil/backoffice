package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.util.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;

    @OneToMany(
            mappedBy = "turma",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER, mappedBy = "turmas")
    private List<Professor> professores = new ArrayList<>();
}
