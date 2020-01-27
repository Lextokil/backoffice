package com.escola.backoffice.boletin;

import com.escola.backoffice.aluno.Aluno;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Boletin {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    private Aluno aluno;

}
