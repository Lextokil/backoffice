package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.Boletim;
import com.escola.backoffice.turma.Turma;
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
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma", referencedColumnName = "id")
    @Getter
    @Setter
    private Turma turma;


    @OneToMany(
            mappedBy = "aluno",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter
    @Setter
    private Set<Boletim> boletins = generateBoletins();

    public Aluno(Long id, String nome, Turma turma) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
    }

    private Set<Boletim> generateBoletins() {
        Set<Boletim> boletimList = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            Boletim b = new Boletim();
            b.setAluno(this);
            boletimList.add(b);
        }
        return boletimList;
    }


}
