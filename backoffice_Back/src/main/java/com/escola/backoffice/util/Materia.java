package com.escola.backoffice.util;

public enum Materia {

    MATEMATICA("MATEMATICA"),
    GEOGRAFIA("GEOGRAFIA"),
    HISTORIA("HISTORIA"),
    BIOLOGIA("BIOLOGIA"),
    FISICA("FISICA"),
    QUIMICA("QUIMICA");


    private final String descricao;

    Materia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
