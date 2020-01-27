package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.Boletin;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.turma.Turma;
import com.escola.backoffice.util.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aluno_professor", joinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_professor", referencedColumnName = "id"))
    private Set<Professor> professores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_turma", referencedColumnName = "id", nullable = false)
    private Turma turma;


    @OneToMany(
            mappedBy = "aluno",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Boletin> boletin = new ArrayList<>();




}
