package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.Boletim;
import com.escola.backoffice.turma.Turma;
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
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma", referencedColumnName = "id")
    private Turma turma;


    @OneToMany(
            mappedBy = "aluno",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Boletim> boletins = generateBoletins();

    public Aluno(Long id, String nome, Turma turma) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
    }

    private List<Boletim> generateBoletins() {
        List<Boletim> boletimList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Boletim b = new Boletim();
            b.setAluno(this);
            boletimList.add(b);
        }
        return boletimList;
    }


}
