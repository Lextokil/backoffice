package com.escola.backoffice.materianota;

import com.escola.backoffice.boletin.Boletim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "materia_nota")
public class MateriaNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "materia")
    private String materia;

    @Column(name = "materia_nota")
    private Double nota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_boletim", referencedColumnName = "id")
    private Boletim boletim;

    public MateriaNota(String materia, Double nota, Boletim boletim) {
        this.materia = materia;
        this.nota = nota;
        this.boletim = boletim;
    }
}
