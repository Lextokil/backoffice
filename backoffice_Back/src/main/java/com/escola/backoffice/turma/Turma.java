package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.util.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turma")
@Getter
@Setter
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
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "turmas")
    private Set<Professor> professores = new HashSet<>();
}
