package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.util.Turno;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    @Getter @Setter
    private Turno turno;

    @OneToMany(
            mappedBy = "turma",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE},
            orphanRemoval = true
    )
    @Getter @Setter
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "turmas")
    @Getter @Setter
    private Set<Professor> professores = new HashSet<>();
}
