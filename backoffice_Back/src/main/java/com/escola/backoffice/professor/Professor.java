package com.escola.backoffice.professor;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.turma.Turma;
import com.escola.backoffice.util.Materia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "professores")
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "professor_turma", joinColumns = @JoinColumn(name = "id_professor", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_turma", referencedColumnName = "id"))
    private Set<Turma> turmas = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "materia")
    private Materia materia;

}
