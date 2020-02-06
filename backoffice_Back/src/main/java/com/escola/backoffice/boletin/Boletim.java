package com.escola.backoffice.boletin;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.materianota.MateriaNota;
import com.escola.backoffice.util.Materia;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boletim")
public class Boletim {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private  Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    @Getter @Setter
    private Aluno aluno;

    @OneToMany(
            mappedBy = "boletim",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<MateriaNota> materiaNotas = generateMaterias();

    public Boletim(Long id, Aluno aluno) {
        this.id = id;
        this.aluno = aluno;

    }

    private List<MateriaNota> generateMaterias(){
        List<MateriaNota> materiaList = new ArrayList<>();
        EnumSet.allOf(Materia.class).forEach(materia -> materiaList.add(new MateriaNota(materia.getDescricao(), 0.0, this)));
        return materiaList;
    }

}
