package com.escola.backoffice.util;

public enum Turno {

    MATUTINO("MATUTINO"),
    VESPERTINO("VESPERTINO"),
    NOTURNO("NORTUNO");

    private final String descricao;

    Turno(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
